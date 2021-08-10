package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
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
            distv.text="Player X Turn"
            Toast.makeText(this, "NEW GAME STARTS", Toast.LENGTH_SHORT).show()
            initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
        for(i in 0..2){
            for (j in 0..2)
            {
                boardStatus[i][j]=-1
            }
        }

        for (i:Array<Button> in board){
            for (btn:Button in i)
            {
                btn.isEnabled=true
                btn.text=""
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
        //horizontal ckeck
        for (i in 0..2)
        {
            if (boardStatus[i][0]==boardStatus[i][1]&&boardStatus[i][0]==boardStatus[i][2])
            {
                if(boardStatus[i][0]==1)
                {
                    updateDislpay("Player X Winner")
                    break
                }
                else if(boardStatus[i][0]==0)
                {
                    updateDislpay("Player O Winner")
                    break
                }
            }
        }
        //vertical check
        for (i in 0..2)
        {
            if (boardStatus[0][i]==boardStatus[1][i]&&boardStatus[0][i]==boardStatus[2][i])
            {
                if(boardStatus[0][i]==1)
                {
                    updateDislpay("Player X Winner")
                    break
                }
                else if(boardStatus[0][i]==0)
                {
                    updateDislpay("Player O Winner")
                    break
                }
            }
        }

        //left diagonal
        if (boardStatus[0][0]==boardStatus[1][1]&&boardStatus[0][0]==boardStatus[2][2])
        {
            if(boardStatus[0][0]==1)
            {
                updateDislpay("Player X Winner")
            }
            else if(boardStatus[0][0]==0)
            {
                updateDislpay("Player O Winner")
            }
        }
        //right diagonal
        if (boardStatus[0][2]==boardStatus[1][1]&&boardStatus[0][2]==boardStatus[2][0])
        {
            if(boardStatus[0][2]==1)
            {
                updateDislpay("Player X Winner")
            }
            else if(boardStatus[0][2]==0)
            {
                updateDislpay("Player O Winner")
            }
        }



    }

    private fun updateDislpay(s: String) {
        distv.text=s
        if(s.contains("Winner"))
        {
            disBtn()
        }

    }
    private fun disBtn()
    {
        for (i:Array<Button> in board)
        {
            for(b:Button in i)
            {
                b.isEnabled=false
            }
        }
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
