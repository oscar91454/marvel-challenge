package com.marvel.challenge.presentation.common

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.marvel.challenge.R
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(bundle: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(bundle)
        setLayout()
    }

    protected fun setLayout() {
        setContentView(getActivityLayout())
    }

    protected abstract fun getActivityLayout(): Int

    open fun showError(errorDescription: String) {
        Toast.makeText(this, errorDescription, Toast.LENGTH_LONG).show()
    }

    open fun showConnectionError() {
        Toast.makeText(this, getString(R.string.connection_error_message), Toast.LENGTH_LONG).show()
    }

    open fun showDefaultError() {
        Toast.makeText(this, getString(R.string.default_error_message), Toast.LENGTH_LONG).show()
    }
}