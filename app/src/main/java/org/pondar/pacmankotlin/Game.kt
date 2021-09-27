package org.pondar.pacmankotlin

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import java.lang.Math.pow
import java.util.ArrayList
import java.util.*
import kotlin.concurrent.schedule
import kotlin.math.sqrt


/**
 *
 * This class should contain all your game logic
 */

class Game(private var context: Context,view: TextView) {

        public var pointsView: TextView = view
        public var points : Int = 0
        //bitmap of the pacman
        var pacBitmap: Bitmap
        var pacx: Int = 0
        var pacy: Int = 0

        var gameisStarted = false;


        //did we initialize the coins?
        var coinsInitialized = false

        //the list of goldcoins - initially empty
        var coins = ArrayList<GoldCoin>()


        //a reference to the gameview
        private lateinit var gameView: GameView
        private var h: Int = 0
        private var w: Int = 0 //height and width of screen


    //The init code is called when we create a new Game class.
    //it's a good place to initialize our images.
    init {
        pacBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.pacman)

    }

    fun setGameView(view: GameView) {
        this.gameView = view
    }

    //TODO initialize goldcoins also here
    fun initializeGoldcoins()
    {
        coins = ArrayList<GoldCoin>()
        //DO Stuff to initialize the array list with some coins.
            for (i in 1..10) {
                coins.add(GoldCoin(context, h, w))
            }
            coinsInitialized = true

    }


    fun newGame() {
        gameisStarted = false;
        pacx = 50
        pacy = 400 //just some starting coordinates - you can change this.
        //reset the points
        coinsInitialized = false
        currentdir = 0
        points = 0
        pointsView.text = "${context.resources.getString(R.string.points)} $points"
        gameView.invalidate() //redraw screen
    }

    fun setSize(h: Int, w: Int) {
        this.h = h
        this.w = w
    }


    var currentdir = 0;
    var canmove = true;

    fun movePacmanRight(pixels: Int) {
        if (pacx + pixels + pacBitmap.width < w) {
            canmove = true;
            pacx = pacx + pixels
            doCollisionCheck()
            gameView.invalidate()
        } else {
            canmove = false;
        }
    }

    fun movePacmanLeft(pixels: Int) {
        //still within our boundaries?
        if (pacx - pixels > 0) {
            canmove = true;
            pacx = pacx - pixels
            doCollisionCheck()
            gameView.invalidate()
        } else {
            canmove = false;
        }
    }

    fun movePacmanUp(pixels: Int) {
        //still within our boundaries?
        if (pacy - pixels > 0) {
            canmove = true;
            pacy = pacy - pixels
            doCollisionCheck()
            gameView.invalidate()
        } else {
            canmove = false;
        }
    }

    fun movePacmanDown(pixels: Int) {
        //still within our boundaries?
        if (pacy + pixels + pacBitmap.height < h) {
            canmove = true;
            pacy = pacy + pixels
            doCollisionCheck()
            gameView.invalidate()
        } else {
            canmove = false;
        }

    }

    fun movepacman(pixels: Int, dir: Int) {
        gameisStarted = true;
        if (dir != currentdir) {
            canmove = true;
            currentdir = dir
            animationLoop(pixels, dir)
        }
    }

    fun animationLoop(pixels: Int, dir: Int) {

        Timer().schedule(20){
            if (dir == 1) {
                movePacmanUp(pixels)
            } else if (dir == 2) {
                movePacmanRight(pixels)
            } else if (dir == 3) {
                movePacmanDown(pixels)
            } else if (dir == 4) {
                movePacmanLeft(pixels)
            }
            if (currentdir == dir && canmove && gameisStarted) {
                animationLoop(pixels, dir)
            }
        }
    }

    //TODO check if the pacman touches a gold coin
    //and if yes, then update the neccesseary data
    //for the gold coins and the points
    //so you need to go through the arraylist of goldcoins and
    //check each of them for a collision with the pacman
    fun doCollisionCheck() {
        var UpdatedCoins = ArrayList<GoldCoin>()
        coins.mapIndexed { i, c ->
            var pacCenterX = pacx+pacBitmap.width/2
            var pacCenterY = pacy+pacBitmap.height/2
            var cointCenterX = c.x+c.cointBitmap.width/2
            var cointCenterY = c.y+c.cointBitmap.height/2


            var distance = sqrt(pow(pacCenterX.toDouble()-cointCenterX.toDouble(), 2.toDouble())+pow(pacCenterY.toDouble()-cointCenterY.toDouble(), 2.toDouble()))

            Log.d("dist", distance.toString())
            if (distance > 80) {
                UpdatedCoins.add(c)
            } else {
                points++

            }
        }
        coins = UpdatedCoins

    }


}