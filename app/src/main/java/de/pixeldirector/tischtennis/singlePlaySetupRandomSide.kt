package de.pixeldirector.tischtennis

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import kotlin.random.Random

class singlePlaySetupRandomSide: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_setup_random_side);

        var spinner_wingameChoose:Int = 2;
        var spinner_singlePointsChoose:Int = 11;
        var name_of_paylerOne:String? = "";
        var name_of_playerTwo:String? = "";
        val randomWinner:Int = Random.nextInt(0, 2)
        val randomTimeInSec:Long = Random.nextInt(3, 6) * 1000.toLong();
        val btn_left_side = findViewById<Button>(R.id.chooseLeftBtn);
        val btn_right_side = findViewById<Button>(R.id.chooseRightBtn);

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
        }

        fun choosePlayer (): String? {
            if (randomWinner == 0) {
                return name_of_paylerOne;
            } else {
                return name_of_playerTwo;
            }
        }

        fun setIntentWithParameter(side: Int) {
            val intentToSingleGame = Intent(this, singleGame::class.java);
            intentToSingleGame.putExtra("spinner_winGameChoose", spinner_wingameChoose);
            intentToSingleGame.putExtra("spinner_singlePointsChoose", spinner_singlePointsChoose);
            intentToSingleGame.putExtra("playerOne", name_of_paylerOne);
            intentToSingleGame.putExtra("playerTwo", name_of_playerTwo);
            intentToSingleGame.putExtra("side", side);
            intentToSingleGame.putExtra("randomWinner", randomWinner);
            startActivity(intentToSingleGame);
        }

        val timerForRandomPlayer = object: CountDownTimer(randomTimeInSec, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("TAG","Zeit der Verlosung: "+ millisUntilFinished/1000);
            }

            override fun onFinish() {
                val progressBarRandom = findViewById<ProgressBar>(R.id.progressBarRandomPlayer);
                val chooseLeftBtn = findViewById<Button>(R.id.chooseLeftBtn);
                val chooseRightBtn = findViewById<Button>(R.id.chooseRightBtn);
                var textWinnerName = findViewById<TextView>(R.id.textWinnerName);
                progressBarRandom.isGone = true;
                chooseLeftBtn.isGone = false;
                chooseRightBtn.isGone = false;
                Log.d("TAG","Zeit abgelaufen!");
                textWinnerName.text = "Gewonnen hat " + choosePlayer() + "\nWähle eine Seite.";
            }
        }
        timerForRandomPlayer.start()

        btn_left_side.setOnClickListener {
            Toast.makeText(this@singlePlaySetupRandomSide, "Ok! Du nimmst die linke Seite.", Toast.LENGTH_SHORT).show();
            setIntentWithParameter(0);
        }
        btn_right_side.setOnClickListener {
            Toast.makeText(this@singlePlaySetupRandomSide, "Ok! Du nimmst die rechte Seite.", Toast.LENGTH_SHORT).show();
            setIntentWithParameter(1);
        }
    }
}