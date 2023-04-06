package com.example.tictactoe

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.databinding.ActivityStartingBinding
import com.google.android.material.textfield.TextInputEditText

class StartingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartingBinding
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStartingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val first =findViewById<TextInputEditText>(R.id.first)
        val second =findViewById<TextInputEditText>(R.id.second)


        val button= binding.button

        radioGroup=binding.radioo
        var startsymbo = "";
        radioGroup.setOnCheckedChangeListener { radiogroup, checkedId ->
            if(checkedId == R.id.secondplayer)
            {
                startsymbo=Names.x
            }
            else
            {
                startsymbo=Names.o
            }
        }

        val a=binding.firstplayer
        val b=binding.secondplayer

        button.setOnClickListener {
            if (first.text?.isNotEmpty() == true && second.text?.isNotEmpty() == true&&startsymbo.isNotEmpty()) {

                val firstT = first.text.toString()
                val secondT = second.text.toString()
                startActivity(Intent(this,MainActivity::class.java).apply {
                    putExtra(Names.player1, firstT)
                    putExtra(Names.player2, secondT)
                    putExtra(Names.currentState,startsymbo)
                })
            }
            if(first.text?.isEmpty() == true){
                first.error= "Please fill both entity"
            }
            if(second.text?.isEmpty() == true){
                second.error= "Please fill both entity"
            }
            if(startsymbo?.isEmpty()==true)
            {
               Toast.makeText(this,getString(R.string.check_one_of_Two),Toast.LENGTH_SHORT).show()
            }
        }
    }
}