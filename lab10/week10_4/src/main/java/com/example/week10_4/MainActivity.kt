package com.example.week10_4

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.core.content.edit
//import com.example.namespace.R


class MainActivity : AppCompatActivity() {
    lateinit var option: SharedPreferences
    lateinit var userInfo: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vBtnSave: Button = findViewById<Button>(R.id.vBtnSave)
        val vSwitchAlarm:Switch = findViewById(R.id.vSwitchAlarm)
        val vBtnMoveActivity:Button = findViewById(R.id.vBtnMoveActivity)
        val vEditName:EditText = findViewById(R.id.vEditName)

        option = getSharedPreferences("option", MODE_PRIVATE)
        userInfo = getSharedPreferences("user_info", MODE_PRIVATE)

        vBtnSave.setOnClickListener {
            var name = vEditName.text.toString()
            userInfo.edit{
                putString("name", name)
                vEditName.setText("")
            }
            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show()
        }

        vSwitchAlarm.setOnCheckedChangeListener { compoundButton, b ->
            option.edit{
                putBoolean("alarm", vSwitchAlarm.isChecked)
            }
        }

        vBtnMoveActivity.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

}
