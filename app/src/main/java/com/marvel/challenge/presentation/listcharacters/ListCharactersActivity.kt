package com.marvel.challenge.presentation.listcharacters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.marvel.challenge.R
import com.marvel.challenge.domain.extensions.launch
import com.marvel.challenge.domain.model.characters.ResultCharacterModel
import com.marvel.challenge.presentation.common.BaseActivity
import com.marvel.challenge.presentation.listcharacters.adapter.ListCharactersAdapter
import com.marvel.challenge.presentation.navigator.Navigator
import com.marvel.challenge.presentation.widget.spinner.SpinnerLoading
import com.marvel.challenge.utils.EndlessRecyclerViewScrollListener
import com.marvel.challenge.utils.getTimeStamp
import com.marvel.challenge.utils.md5
import kotlinx.android.synthetic.main.activity_list_characters.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class ListCharactersActivity : BaseActivity(), ListCharactersView,
    ListCharactersAdapter.ClickListener,
    SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var presenter: ListCharactersPresenter

    private val adapter: ListCharactersAdapter = ListCharactersAdapter(listener = this)

    @Inject
    lateinit var spinnerLoading: SpinnerLoading

    private var endlessScrollListener: EndlessRecyclerViewScrollListener? = null

    lateinit var apikey: String

    lateinit var privateKey: String

    companion object {
        fun buildIntent(context: Context): Intent {
            return Intent(context, ListCharactersActivity::class.java)
        }
    }

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        presenter.attachView(this)
        initToolbar()
        initRecycler()
        initUI()
        presenter.getListCharacters(
            10,
            getTimeStamp(),
            apikey,
            md5(getTimeStamp(), privateKey, apikey)
        )
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = getString(R.string.app_name)
    }

    private fun initUI() {
        srListCharacters.setOnRefreshListener(this)
        apikey = this.getString(R.string.KEY_MARVEL_PUBLIC_KEY)
        privateKey = this.getString(R.string.KEY_MARVEL_PRIVATE_KEY)

        val linearLayoutManager = LinearLayoutManager(this)
        rvListCharacters.layoutManager = linearLayoutManager
        endlessScrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(
                page: Int,
                totalItemsCount: Int,
                view: RecyclerView
            ) {
                launch {
                    presenter.getListCharacters(
                        limit = presenter.limit + 10,
                        getTimeStamp(), apikey,
                        md5(getTimeStamp(), privateKey, apikey)
                    )
                }

            }
        }.also {
            rvListCharacters.addOnScrollListener(it)
        }

        rvListCharacters.adapter = adapter

    }

    private fun initRecycler() {
        rvListCharacters.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        setDividerHorizontal(rvListCharacters)
    }


    private fun setDividerHorizontal(recyclerView: androidx.recyclerview.widget.RecyclerView) {
        this.let {
            val drawableVertical =
                ContextCompat.getDrawable(it, R.drawable.divider_horizontal)
            val itemDecoratorVertical = DividerItemDecoration(it, DividerItemDecoration.VERTICAL)
            drawableVertical?.let {
                itemDecoratorVertical.setDrawable(drawableVertical)
                recyclerView.addItemDecoration(itemDecoratorVertical)
            }
        }
    }

    override fun getActivityLayout() = R.layout.activity_list_characters


    override fun fillListCharacters(listCharacters: List<ResultCharacterModel>) {
        srListCharacters.isRefreshing = false
        adapter.setCharacters(listCharacters)
        endlessScrollListener?.resetState()
        adapter.notifyDataSetChanged()
    }

    override fun updateListCharacters(listCharacters: List<ResultCharacterModel>) {
        srListCharacters.isRefreshing = false
        adapter.addMoreCharacters(listCharacters)
        adapter.notifyDataSetChanged()
    }

    override fun hideSwipeLoading() {
        srListCharacters.isRefreshing = false
    }

    override fun showLoading() {
        spinnerLoading.show(this)
    }

    override fun hideLoading() {
        spinnerLoading.dismiss()
    }

    override fun onRefresh() {
        launch {
            presenter.getListCharacters(
                10, getTimeStamp(), apikey,
                md5(getTimeStamp(), privateKey, apikey)
            )
        }
    }


    override fun RowClick(character: ResultCharacterModel) {
        navigator.navigateToCharacterDetails(this, character.id)
    }

}