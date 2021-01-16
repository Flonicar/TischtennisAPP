package de.pixeldirector.tischtennis

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class singleGame: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_game);

        val spinner_wingameChoose:Int;
        val spinner_singlePointsChoose:Int;
        var name_of_paylerOne:String? = "";
        var name_of_playerTwo:String? = "";
        var winner:Int = 0;
        var side:Int = 0;
        val namePlayerLeftSide = findViewById<TextView>(R.id.namePlayerLeftSide);
        val winningSetPlayerLeftSide = findViewById<TextView>(R.id.winningSetPlayerLeftSide);
        val winningSetPlayerRightSide = findViewById<TextView>(R.id.winningSetPlayerRightSide);
        val namePlayerRightSide = findViewById<TextView>(R.id.namePlayerRightSide);
        val pointsLeftSide = findViewById<TextView>(R.id.pointsLeftSide);
        val pointsRightSide = findViewById<TextView>(R.id.pointsRightSide);
        val serveLeftSide = findViewById<TextView>(R.id.serveLeftSide);
        val serveRightSide = findViewById<TextView>(R.id.serveRightSide);
        var winningSetsPlayerOne:Int = 0;
        var winnimgSetsPlayerTwo:Int = 0;
        var pointsPlayerLeft:Int = 0;
        var pointsPlayerRight:Int = 0;
        var countBallChange:Int = 0;
        var playerLeft:String? = "";
        var playerRight:String? = "";
        var leftOrRight:Boolean = true;


        val bundle :Bundle ?=intent.extras
        if (bundle!=null) {
            spinner_wingameChoose = bundle.getInt("spinner_winGameChoose");
            Log.d("TAG","Anzahl der Spiele: "+ spinner_wingameChoose);
            spinner_singlePointsChoose = bundle.getInt("spinner_singlePointsChoose");
            Log.d("TAG","Punkte pro Satz: "+ spinner_singlePointsChoose);
            name_of_paylerOne = bundle.getString("playerOne");
            Log.d("TAG","Name Spieler 1: "+ name_of_paylerOne);
            name_of_playerTwo = bundle.getString("playerTwo");
            Log.d("TAG","Name Spieler 2: "+ name_of_playerTwo);
            side = bundle.getInt("side");
            Log.d("TAG","Seitenwahl: "+ side);
            winner = bundle.getInt("randomWinner");
            Log.d("TAG","Gewinner der Verlosung: "+ winner);
        }

        fun serveLeft() {
            serveLeftSide.text = "1";
            serveRightSide.text = "0";
        }

        fun serveRight() {
            serveLeftSide.text = "0";
            serveRightSide.text = "1";
        }

        fun setPointsPlayerOneLeftToView() {
            pointsLeftSide.text = pointsPlayerLeft.toString();
            pointsRightSide.text = pointsPlayerRight.toString();
        }

        fun setPointsPlayerOneRightToView() {
            pointsLeftSide.text = pointsPlayerRight.toString();
            pointsRightSide.text = pointsPlayerLeft.toString();
        }

        fun setPlayernameToView() {
            namePlayerLeftSide.text = playerLeft;
            namePlayerRightSide.text = playerRight;
        }

        fun initzialGame() {
            if (side == 0 && winner == 0) {
                playerLeft = name_of_paylerOne;
                playerRight = name_of_playerTwo;
                leftOrRight = true;
                setPlayernameToView();
                serveLeft();
                setPointsPlayerOneLeftToView()
            } else if (side == 0 && winner == 1) {
                playerLeft = name_of_playerTwo;
                playerRight = name_of_paylerOne;
                leftOrRight = true;
                setPlayernameToView();
                serveLeft();
                setPointsPlayerOneRightToView()
            } else if (side == 1 && winner == 0) {
                playerLeft = name_of_playerTwo;
                playerRight = name_of_paylerOne;
                leftOrRight = false;
                setPlayernameToView();
                serveRight();
                setPointsPlayerOneRightToView()
            } else if (side == 1 && winner == 1) {
                playerLeft = name_of_paylerOne;
                playerRight = name_of_playerTwo;
                leftOrRight = false;
                setPlayernameToView();
                serveRight();
                setPointsPlayerOneLeftToView()
            }
        }

        fun checkServe(){
            countBallChange = countBallChange.inc();
            if (countBallChange % 2 == 0) {
                if (leftOrRight) {
                    serveRight();
                    leftOrRight = false;
                } else {
                    serveLeft();
                    leftOrRight = true;
                }
            }
        }

        fun leftPlayerGetPoint() {
            pointsPlayerLeft = pointsPlayerLeft.inc();
            checkServe()
            pointsLeftSide.text = pointsPlayerLeft.toString();
        }

        fun rightPlayerGetPoint() {
            pointsPlayerRight = pointsPlayerRight.inc();
            checkServe()
            pointsRightSide.text = pointsPlayerRight.toString();
        }

        fun gamePlay() {
            val leftSideBtn = findViewById<TextView>(R.id.pointsLeftSide);
            leftSideBtn.setOnClickListener(){
                Toast.makeText(this@singleGame, "Links hat einen Punkt. Super!", Toast.LENGTH_SHORT).show();
                leftPlayerGetPoint();
            }
            val rightSideBtn = findViewById<TextView>(R.id.pointsRightSide);
            rightSideBtn.setOnClickListener {
                Toast.makeText(this@singleGame, "Rechts hat einen Punkt. Klasse!", Toast.LENGTH_SHORT).show();
                rightPlayerGetPoint();
            }
        }

        initzialGame();
        gamePlay();
    }
}

