package com.zomaotoko.pokedex.pokelist

import android.app.Fragment
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.zomaotoko.pokedex.R
import com.zomaotoko.pokedex.apirequest.POKEAPI_URL
import com.zomaotoko.pokedex.apirequest.PokemonService
import com.zomaotoko.pokedex.dto.APIResource
import com.zomaotoko.pokedex.dto.endpoint.PokemonEndPointResponse
import com.zomaotoko.pokedex.dto.pokedata.Pokemon

import kotlinx.android.synthetic.main.fragment_poke_list.*
import org.jetbrains.anko.runOnUiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeListFragment : Fragment() {
    private lateinit var viewAdapter: PokeListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var pokeList: ArrayList<Pokemon> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewManager = LinearLayoutManager(activity)
        viewAdapter = PokeListAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_poke_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(recyclerView) {
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
            addItemDecoration(DividerItemDecoration(
                    recyclerView.context,
                    LinearLayoutManager.VERTICAL
            ))
        }
    }

    override fun onResume() {
        super.onResume()
        populatePokeList()
    }

    var zoomableContainer: ImageView? = null
        set(imageView) {
            viewAdapter.zoomContainer = imageView
        }

    // API calls

    private fun populatePokeList(url: String? = null) {
        val service = buildPokemonService()
        val call = if (url == null) service.getEndPointResource() else service.getEndPointResource(url)
        call.enqueue(object : Callback<PokemonEndPointResponse> {
            override fun onFailure(call: Call<PokemonEndPointResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<PokemonEndPointResponse>?, response: Response<PokemonEndPointResponse>?) {
                val endPointResponse = response?.body()
                endPointResponse?.let {
                    AsyncTask.execute {
                        it.results?.forEach { apiResource ->
                            getPokemonResource(apiResource)
                            runOnUiThread {
                                viewAdapter.updateDataSet(pokeList)
                            }
                        }

                        if (it.next != null) {
                            populatePokeList(it.next)
                        }
                    }
                }
            }
        })
    }

    private fun getPokemonResource(resource: APIResource) {
        val service = buildPokemonService()
        val pokemon = service.getPokemonResource(resource.url).execute().body()
        if (pokemon != null) {
            pokeList.add(pokemon)
        }
    }

    private fun buildPokemonService() = createRetrofitInstance().create(PokemonService::class.java)

    private fun createRetrofitInstance() = Retrofit
            .Builder()
            .baseUrl(POKEAPI_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    /*
    * val retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/")
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

            })*/
}