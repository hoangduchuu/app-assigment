package com.hdhuu.home

import com.hdhuu.models.Candy

/**
 * Created by Huu Hoang on 4/3/19.
 */

interface MainContract{
    interface View{
        fun onGetDataSuccess(data: List<Candy>)
        fun fetchData()
    }
    interface Presenter{
        fun getCandies()
        fun createSampleCandies()
    }
}