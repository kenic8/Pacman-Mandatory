package org.pondar.pacmankotlin

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import org.pondar.pacmankotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //reference to the game class.
    private lateinit var game: Game
    private lateinit var binding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //makes sure it always runs in portrait mode - will cost a warning
        //but this is want we want!
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Log.d("onCreate","Oncreate called")

        game = Game(this,binding.pointsView, this.binding.timerView, this.binding.levelView)

        //intialize the game view clas and game class
        game.setGameView(binding.gameView)
        binding.gameView.setGame(game)
        game.newGame()

        binding.pause.setOnClickListener {
            game.paused = !game.paused;
        }

        binding.moveRight.setOnClickListener {
            game.paused = false;
            game.movepacman(10, 2)

        }
        binding.moveLeft.setOnClickListener {
            game.paused = false;
            game.movepacman(10, 4)

        }
        binding.moveUp.setOnClickListener {
            game.paused = false;
            game.movepacman(10, 1)

        }
        binding.moveDown.setOnClickListener {
            game.paused = false;
            game.movepacman(10, 3)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_settings) {
            Toast.makeText(this, "settings clicked", Toast.LENGTH_LONG).show()
            return true
        } else if (id == R.id.action_newGame) {
            Toast.makeText(this, "New Game clicked", Toast.LENGTH_LONG).show()
            game.newGame()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
