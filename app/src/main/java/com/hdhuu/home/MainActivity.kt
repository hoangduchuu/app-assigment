package com.hdhuu.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hdhuu.R
import com.hdhuu.models.Candy
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainContract.View, CandyItemClickListener{
    private var data: ArrayList<Candy> = ArrayList()

    private val presenter: MainContract.Presenter  by inject { parametersOf(this) }
    private lateinit var candyAdapter: CandyAdapter
    private var currentItemPosition= -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewNodata.setOnClickListener(onGenerateButtonCLickListener)

        fetchData()

        setupRV()

        currentItemView.setOnClickListener {
            onCurrentItemClicked()
        }

    }

    private fun setupRV() {
        candyAdapter = CandyAdapter(data,this)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.addItemDecoration(ListPaddingDecoration(this, 12, 12))
        rv.adapter = candyAdapter
        candyAdapter.setListener(this)
    }

    private val onGenerateButtonCLickListener = View.OnClickListener {
        presenter.createSampleCandies()
    }


    override fun fetchData() {
       presenter.getCandies()
    }

    override fun onGetDataSuccess(data: List<Candy>) {
        this.data = data as ArrayList<Candy>
        candyAdapter.setdata(data)
        candyAdapter.notifyDataSetChanged()

    }

    override fun showEmptyView() {
        viewNodata.visibility = View.VISIBLE
    }

    override fun hideEmptyView() {
        viewNodata.visibility = View.GONE
    }

    override fun onItemClicked(candy: Candy, position: Int) {
        currentItemView.visibility = View.VISIBLE
        currentItemPosition = position
        increateAndChangeColorCurrentItem(candy.eatingCount!!, candy.color)

    }

    override fun increateAndChangeColorCurrentItem(eatingCount: Int, color: Int?) {
        tvCurrentItem.text = eatingCount.toString()
        currentItemView.setBackgroundColor(color!!)
    }

    private fun onCurrentItemClicked(){
        presenter.increaseEatingCount(data[currentItemPosition])
    }
}
