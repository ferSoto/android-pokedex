package com.zomaotoko.pokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = getColor(R.color.colorPrimaryDark)
        setSupportActionBar(toolbar)
        updateToolbar()
    }

    private fun updateToolbar() {
        toolbar.navigationIcon = getDrawable(R.drawable.ic_pokeball)
        invalidateOptionsMenu()
    }
}
