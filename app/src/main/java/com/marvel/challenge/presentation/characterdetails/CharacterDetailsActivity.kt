package com.marvel.challenge.presentation.characterdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import com.marvel.challenge.R
import com.marvel.challenge.domain.model.characters.ResultCharacterModel
import com.marvel.challenge.presentation.common.BaseActivity
import com.marvel.challenge.presentation.widget.spinner.SpinnerLoading
import com.marvel.challenge.utils.getTimeStamp
import com.marvel.challenge.utils.md5
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_detail_character.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


class CharacterDetailsActivity : BaseActivity(), CharacterDetailsView {

    @Inject
    lateinit var presenter: CharacterDetailsPresenter

    lateinit var apikey: String

    lateinit var privateKey: String


    @Inject
    lateinit var spinnerLoading: SpinnerLoading

    companion object {
        private const val KEY_CHARACTER_ID = "key_character_id"

        fun buildIntent(context: Context, characterId: Int): Intent {
            val intent = Intent(context, CharacterDetailsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra(KEY_CHARACTER_ID, characterId)
            return intent
        }
    }

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        presenter.attachView(this)
        initToolbar()
        initListeners()
        initUI()
    }


    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun initListeners() {
        ivBack.setOnClickListener { finish() }
        ivShare.setOnClickListener { share() }
    }

    private fun initUI() {
        apikey = this.getString(R.string.KEY_MARVEL_PUBLIC_KEY)
        privateKey = this.getString(R.string.KEY_MARVEL_PRIVATE_KEY)
        presenter.getCharacterDetailsData(
            getCharacterId(), getTimeStamp(), apikey,
            md5(getTimeStamp(), privateKey, apikey)
        )
    }

    private fun share() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Share")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

    }

    override fun getActivityLayout() = R.layout.item_detail_character

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return false
    }

    override fun fillCharactersDetails(resultCharacterModel: ResultCharacterModel) {
        val urlImage =
            resultCharacterModel.thumbnail.path + "." + resultCharacterModel.thumbnail.extension

        Picasso.get().load(urlImage).into(ivDetailCharacter)
        tvTitleCharacter.text = resultCharacterModel.name

        if (resultCharacterModel.urls.urls.isNotEmpty()) {
            tvDetailTitle.visibility = VISIBLE
            tvUrlDetailLink.visibility = VISIBLE
            tvUrlDetailLink.text = resultCharacterModel.urls.urls[0].url
            tvUrlDetailLink.movementMethod = LinkMovementMethod.getInstance()
        } else {
            tvDetailTitle.visibility = GONE
            tvUrlDetailLink.visibility = GONE
        }
    }

    override fun getCharacterId() =
        intent.extras?.getSerializable(KEY_CHARACTER_ID) as Int

    override fun showLoading() {
        spinnerLoading.show(this)
    }

    override fun hideLoading() {
        spinnerLoading.dismiss()
    }

}