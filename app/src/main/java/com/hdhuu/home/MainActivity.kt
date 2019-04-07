package com.hdhuu.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hdhuu.R
import com.hdhuu.models.Candy
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainContract.View{
    private var data: ArrayList<Candy> = ArrayList()

    private val presenter: MainContract.Presenter  by inject { parametersOf(this) }
    private lateinit var candyAdapter: CandyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ln.setOnLongClickListener(appLongClickListener)

        fetchData()

        setupRV()

    }

    private fun setupRV() {
        candyAdapter = CandyAdapter(data,this)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.addItemDecoration(ListPaddingDecoration(this, 12, 12))
        rv.adapter = candyAdapter
    }

    private val appLongClickListener = View.OnLongClickListener {
        presenter.createSampleCandies()
        return@OnLongClickListener true
    }


    override fun fetchData() {
       presenter.getCandies()
    }

    override fun onGetDataSuccess(data: List<Candy>) {
        this.data = data as ArrayList<Candy>
        candyAdapter.setdata(data)
        candyAdapter.notifyDataSetChanged()

    }
}
