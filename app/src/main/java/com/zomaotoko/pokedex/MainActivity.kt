package com.zomaotoko.pokedex

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.zomaotoko.pokedex.apirequest.BerriesService
import com.zomaotoko.pokedex.dto.berries.Berry
import com.zomaotoko.pokedex.pokelist.PokeListFragment
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var pokeListFragment: PokeListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        pokeListFragment = (fragmentManager.findFragmentById(R.id.poke_fragment_list) as PokeListFragment)
        configureToolbar()
        button.setOnClickListener {
            val retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/")
                    .addConverterFactory(GsonConverterFactory.create()).build()
            val service = retrofit.create(BerriesService::class.java)
            val call = service.getBerry(1)
            call.enqueue(object: Callback<Berry> {
                override fun onFailure(call: Call<Berry>?, t: Throwable?) {

                }

                override fun onResponse(call: Call<Berry>?, response: Response<Berry>?) {
                    val berry = response?.body()
                    berry.toString()
                }

            })
        }
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
}
