package com.example.intentactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val data = intent.getStringExtra("data")
        val btn: Button = findViewById(R.id.btn_sub)
        val ev = findViewById<EditText>(R.id.ev_sub)
        val res: TextView = findViewById(R.id.res_sub)
        res.text = "$data\n-send from main"

        btn.setOnClickListener {
            if (ev.text.isEmpty()) {
                Toast.makeText(this, "값을 입력 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val data: String = ev.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", data)
            setResult(Activity.RESULT_OK, intent)
            ev.text = null
            finish()
        }
    }
}

