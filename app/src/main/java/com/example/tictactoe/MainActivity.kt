package com.example.tictactoe

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var player1:TextView
    lateinit var player2:TextView

    lateinit var imageView1: ImageView
    lateinit var imageView2: ImageView

    var activePlayer = 0
    var moves: Array<Int> = arrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)

    var winPos = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6), intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.hide()

        player1=findViewById(R.id.f)
        player2=findViewById(R.id.s)

        val name1=intent.getStringExtra("player1")
        val name2=intent.getStringExtra("player2")

        val checker= intent.getStringExtra("currentState")

        if (checker=="x") {
            player1.text = name2
            player2.text = name1
        }
        else if (checker=="o")
        {
            player1.text = name1
            player2.text = name2
        }
        imageView1=findViewById(R.id.x)
        imageView2=findViewById(R.id.o)


    }

    var isWinner :Int = -1
    private var count :Int =0

    fun tap(view: View) {
        if (view is ImageView) {

            val clickedTag = view.tag?.toString()?.toInt()
            //Log.d( "tag","tag is $clickedTag")
            // Get the tag from the ImageView
            if(activePlayer==0&& moves[clickedTag as Int]==-1)
            {
                moves[clickedTag]=0
                view.setImageResource(R.drawable.o)
                activePlayer=1
                imageView1.visibility=View.VISIBLE
                imageView2.visibility=View.GONE
                count += 1
            }
            else if(activePlayer==1 && moves[clickedTag as Int]==-1)
            {
                moves[clickedTag]=1
                imageView2.visibility=View.VISIBLE
                imageView1.visibility=View.GONE
                view.setImageResource(R.drawable.cross)
                activePlayer=0
                count += 1
            }
           // Toast.makeText(this,"$isWinner",Toast.LENGTH_SHORT).show()
        }
        var winn=false

        winPos.forEachIndexed { index, ints ->
            val v1 = winPos[index][0]
            val v2 = winPos[index][1]
            val v3 = winPos[index][2]

            if (moves[v1] == moves[v2] && moves[v2] == moves[v3] && moves[v1] != -1)
            {
                isWinner = moves[v1]
                // Break out of the loop when condition is met
                Toast.makeText(this,"$isWinner's team win the game",Toast.LENGTH_SHORT).show()
                ShowCustomAlertDialog(this)
                winn = true
                return@forEachIndexed
            }
            if(count==9 && !winn){
                val alertDialog = AlertDialog.Builder(this)
                val inflater = LayoutInflater.from(this)
                val dialogView = inflater.inflate(R.layout.winner, null)

                alertDialog.setView(dialogView)
                alertDialog.setCancelable(false)

                val winner = dialogView.findViewById<TextView>(R.id.nameOfWinner)
                val okk = dialogView.findViewById<Button>(R.id.okk)
                val win = dialogView.findViewById<TextView>(R.id.winner)
                winner.text="Try Again"
                win.text="draw"

                val dialog = alertDialog.create()

                okk.setOnClickListener {
                    // Do something when OK button is clicked
                    val intent=Intent(this,StartingActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                dialog.show()
            }
        }

    }


    // Custom Alert Dialog
    fun ShowCustomAlertDialog(context: Context) {
        val alertDialog = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.winner, null)

        alertDialog.setView(dialogView)
        alertDialog.setCancelable(false)

        val winner = dialogView.findViewById<TextView>(R.id.nameOfWinner)
        val okk = dialogView.findViewById<Button>(R.id.okk)
        if(isWinner==0) {
            winner.text =player1.text
        }
        else if (isWinner==1)
        {

            winner.text =player2.text

        }
        val dialog = alertDialog.create()

        okk.setOnClickListener {
            // Do something when OK button is clicked
            val intent=Intent(this,StartingActivity::class.java)
            startActivity(intent)
            finish()
        }
        dialog.show()
    }
}
