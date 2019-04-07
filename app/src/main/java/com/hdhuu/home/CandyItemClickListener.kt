package com.hdhuu.home

import com.hdhuu.models.Candy

/**
 * Created by Huu Hoang on 4/8/19.
 */

interface CandyItemClickListener{
    fun onItemClicked(candy: Candy, position: Int)
}