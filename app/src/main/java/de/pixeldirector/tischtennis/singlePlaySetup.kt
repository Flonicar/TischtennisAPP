package de.pixeldirector.tischtennis

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class singlePlaySetupActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_setup)

        val btn_singleSetupBackBtn = findViewById(R.id.singleSetupBackBtn) as Button
        btn_singleSetupBackBtn.setOnClickListener {
            Toast.makeText(this@singlePlaySetupActivity, "Und wieder zurück!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }

        val array_wingame = resources.getStringArray(R.array.wingames_array);
        val array_singlePoints = resources.getStringArray(R.array.single_points_array);
        val btn_nextStepSetup = findViewById<Button>(R.id.playSingleGame);
        var spinner_wingameChoose = array_wingame[0].toInt();
        var spinner_singlePointsChoose = array_singlePoints[0].toInt();

        val spinner_wingames = findViewById<Spinner>(R.id.spinnerWinGames);
        if (spinner_wingames != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, array_wingame);
            spinner_wingames.adapter = adapter;

            spinner_wingames.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@singlePlaySetupActivity, "Also " + array_wingame[position] + " Sätze wollt ihr spielen.", Toast.LENGTH_SHORT).show();
                    spinner_wingameChoose = array_wingame[position].toInt();
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        val spinner_points = findViewById<Spinner>(R.id.spinnerPoints);
        if (spinner_points != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, array_singlePoints);
            spinner_points.adapter = adapter;

            spinner_points.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Toast.makeText(this@singlePlaySetupActivity, "Ok. Also bis " + array_singlePoints[position] + " willst du spielen.", Toast.LENGTH_SHORT).show();
                    spinner_singlePointsChoose = array_singlePoints[position].toInt();
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
        btn_nextStepSetup.setOnClickListener {
            Toast.makeText(this@singlePlaySetupActivity, "Mal schauen, wer Aufschlag hat.", Toast.LENGTH_SHORT).show();
            val intentToSingleRandom = Intent(this, singlePlaySetupRandomSide::class.java);
            intentToSingleRandom.putExtra("spinner_winGameChoose", spinner_wingameChoose);
            intentToSingleRandom.putExtra("spinner_singlePointsChoose", spinner_singlePointsChoose);
            intentToSingleRandom.putExtra("playerOne", findViewById<EditText>(R.id.playerName1).text.toString());
            intentToSingleRandom.putExtra("playerTwo", findViewById<EditText>(R.id.playerName2).text.toString());
            startActivity(intentToSingleRandom);
        }
    }
}