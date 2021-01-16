package de.pixeldirector.tischtennis

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.time.Instant


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_gotToSingle = findViewById(R.id.goToSingle) as Button
        val btn_goToDouble = findViewById(R.id.goToDouble) as Button

        btn_gotToSingle.setOnClickListener {
            Toast.makeText(this@MainActivity, "So so .... Einzelspieler!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, singlePlaySetupActivity::class.java))
        }
        btn_goToDouble.setOnClickListener {
            Toast.makeText(this@MainActivity, "Aja... zu zweit gegeneinander.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, doublePlaySetupActivity::class.java))
        }
    }
}