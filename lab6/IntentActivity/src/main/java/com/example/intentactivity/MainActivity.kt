package com.example.intentactivity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn: Button = findViewById(R.id.btn_main)
        val ev = findViewById<EditText>(R.id.ev_main)
        val res: TextView = findViewById(R.id.res_main)

        btn.setOnClickListener {
            if (ev.text.isEmpty()) {
                Toast.makeText(this, "값을 입력 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val data: String = ev.text.toString()
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("data", data)
            startActivityForResult(intent, 100)
            ev.text = null
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val res: TextView = findViewById(R.id.res_main)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                100 -> {
                    res.text = data!!.getStringExtra("data").toString() + "\n-send from sub"
                }
            }
        }
    }
}

