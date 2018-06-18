package com.zomaotoko.pokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.zomaotoko.pokedex.pokelist.PokeListFragment
import com.zomaotoko.pokedex.pokemon.PokemonFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PokeListFragment.ItemClickListener {
    private lateinit var pokeListFragment: PokeListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokeListFragment = supportFragmentManager.findFragmentById(R.id.poke_fragment_list) as PokeListFragment
        pokeListFragment.listener = this
        configureToolbar()
    }

    private fun configureToolbar() {
        window.statusBarColor = getColor(R.color.colorPrimaryDark)
        setSupportActionBar(toolbar)
        updateToolbar()
    }

    private fun updateToolbar() {
        toolbar.navigationIcon = getDrawable(R.drawable.ic_pokeball)
        invalidateOptionsMenu()
    }

    override fun onStart() {
        super.onStart()
        zoomedImage.post {
            pokeListFragment.zoomableContainer = zoomedImage
        }
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


    // Listeners

    override fun onItemClick(id: Int) {
        addFragmentAnimated(PokemonFragment.getInstance(id), "mytag")
    }


    // Helper methods

    private fun addFragmentAnimated(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.fragment_slide_in, R.animator.fragment_slide_out, R.animator.fragment_slide_in, R.animator.fragment_slide_out)
                .add(fragmentContainer.id, fragment, tag)
                .addToBackStack(null)
                .commit()
    }
}
