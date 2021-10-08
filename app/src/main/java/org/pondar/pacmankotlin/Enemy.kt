
package org.pondar.pacmankotlin
import android.graphics.Bitmap
import android.util.Log
import kotlin.random.Random

class Enemy ( h: Int, w: Int, bitmap: Bitmap, pacY:Int, pacX:Int) {


    var y: Int
    var x: Int


    var EnemyBitmap: Bitmap = bitmap



    init {
      y = pacY+100
        x = pacX+100
    }

    init {
        var yTry = Random.nextInt(0, h - 200)
        var xTry = Random.nextInt(0, w - 200)
        if (yTry != pacY && xTry != pacX)
            y = yTry+80
            x = xTry+80
    }


}