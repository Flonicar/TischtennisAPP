package de.pixeldirector.tischtennis

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class doublePlaySetupActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.double_setup)

        val btn_doubleSetupBackBtn = findViewById(R.id.doubleSetupBackBtn) as Button
        btn_doubleSetupBackBtn.setOnClickListener {
            Toast.makeText(this@doublePlaySetupActivity, "Und wieder zurück!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}