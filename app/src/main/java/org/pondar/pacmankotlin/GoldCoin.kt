package org.pondar.pacmankotlin

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.TextView
import kotlin.math.log
import kotlin.random.Random

//Here you need to fill out what should be in a GoldCoin and what should the constructor be
class GoldCoin(context: Context, h: Int, w: Int) {

    var y: Int
    var x: Int

    var cointBitmap: Bitmap

    init {

        cointBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.coin)
        Log.d("hej",h.toString())
        y = Random.nextInt(0, h-200)
        x = Random.nextInt(0, w-200)
    }

}