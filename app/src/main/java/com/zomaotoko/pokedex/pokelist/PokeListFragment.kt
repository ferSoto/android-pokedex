package com.zomaotoko.pokedex.pokelist

import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.zomaotoko.pokedex.R
import com.zomaotoko.pokedex.dto.APIResource

import kotlinx.android.synthetic.main.fragment_poke_list.*

class PokeListFragment : Fragment() {
    private lateinit var viewAdapter: PokeListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var pokeList: ArrayList<APIResource> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewManager = LinearLayoutManager(activity)
        viewAdapter = PokeListAdapter(activity = activity!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_poke_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(PokeListViewModel::class.java)
        with(recyclerView) {
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
            addItemDecoration(DividerItemDecoration(
                    recyclerView.context,
                    LinearLayoutManager.VERTICAL
            ))
        }

        viewModel.pokeListElements.observe(this, Observer {
            viewAdapter.updateDataSet(it!!)
        })
    }

    var zoomableContainer: ImageView? = null
        set(imageView) {
            viewAdapter.zoomContainer = imageView
        }
}