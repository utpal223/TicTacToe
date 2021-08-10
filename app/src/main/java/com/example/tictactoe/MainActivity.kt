package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener  {

    var player = true
    var turncount=0
    var boardStatus= Array(3){IntArray(3)}
    lateinit var board:Array<Array<Button>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board= arrayOf(
            arrayOf(btn1,btn2,btn3),
            arrayOf(btn4,btn5,btn6),
            arrayOf(btn7,btn8,btn9)
        )

        for (i:Array<Button> in board)
        {
            for(button:Button in i)
            {
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()
        reset.setOnClickListener {
            player=true
            turncount=0
            initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
        for(i in 0..2){
            for (i in 0..2)
            {
                boardStatus[0][0]=-1
                board[0][0].isEnabled=true
                board[0][0].text=""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btn1 ->{
                updateValue(row=0,col=0,play=player)
            }
            R.id.btn2 ->{
                updateValue(row=0,col=1,play=player)
            }
            R.id.btn3 ->{
                updateValue(row=0,col=2,play=player)
            }
            R.id.btn4 ->{
                updateValue(row=1,col=0,play=player)
            }
            R.id.btn5 ->{
                updateValue(row=1,col=1,play=player)
            }
            R.id.btn6 ->{
                updateValue(row=1,col=2,play=player)
            }
            R.id.btn7 ->{
                updateValue(row=2,col=0,play=player)
            }
            R.id.btn8 ->{
                updateValue(row=2,col=1,play=player)
            }
            R.id.btn9 ->{
                updateValue(row=2,col=2,play=player)
            }
        }
        turncount++
        player=!player
        if (player){
            updateDislpay("Player X Turn")
        }
        else
        {
            updateDislpay("Player O Turn")
        }
        if (turncount==9){
            updateDislpay("Game Draw !")
        }
        checkWinner()

    }

    private fun checkWinner() {
        //horizontal
        for (i in 0..2)
        {
             
        }
    }

    private fun updateDislpay(s: String) {
        distv.text=s
    }

    private fun updateValue(row: Int, col: Int, play: Boolean) {
        val text=if(player) "X" else "0"
        val value=if(player) 1 else 0
        board[row][col].apply {
            isEnabled=false

            setText(text)
        }
        boardStatus[row][col]=value
        }

    }
