package com.hdhuu.data.utils

import android.graphics.Color
import java.util.*

/**
 * Created by Huu Hoang on 4/8/19.
 */


object ColorHelper {
    fun getRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

    }
}