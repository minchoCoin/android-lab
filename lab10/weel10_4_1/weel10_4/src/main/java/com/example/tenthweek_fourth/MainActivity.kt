package com.example.tenthweek_fourth

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    lateinit var option: SharedPreferences
    lateinit var userInfo: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        option = getSharedPreferences("option", MODE_PRIVATE)
        userInfo = getSharedPreferences("user_info", MODE_PRIVATE)

        val move = findViewById<Button>(R.id.next)
        val save = findViewById<Button>(R.id.save)
        val switch = findViewById<Switch>(R.id.vSwitchAlarm)
        val res = findViewById<EditText>(R.id.edit)

        save.setOnClickListener {
            userInfo.edit{
                putString("name", "${res.text}")
                commit()
            }
            Toast.makeText(this, "Name Save!!", Toast.LENGTH_SHORT).show()
        }

        switch.setOnCheckedChangeListener { compoundButton, b ->
            option.edit{
                putBoolean("alarm", switch.isChecked)
            }
        }

        move.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        saveValueLoad()
        super.onStart()
    }

    private fun saveValueLoad(){
        val res = findViewById<EditText>(R.id.edit)
        res.setText(userInfo.getString("name",""))

        val switch = findViewById<Switch>(R.id.vSwitchAlarm)
        switch.isChecked = option.getBoolean("alarm", false)
    }

}
