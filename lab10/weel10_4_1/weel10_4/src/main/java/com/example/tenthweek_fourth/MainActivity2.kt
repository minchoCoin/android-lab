package com.example.tenthweek_fourth

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity2 : AppCompatActivity() {
    lateinit var option: SharedPreferences
    lateinit var userInfo: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        option = getSharedPreferences("option", MODE_PRIVATE)
        userInfo = getSharedPreferences("user_info", MODE_PRIVATE)
        var str = """User Name: ${userInfo.getString("name", "NULL")}
            |
            |Alarm On/Off: ${boolToText(option.getBoolean("alarm", false))}
        """.trimMargin()
        val res = findViewById<TextView>(R.id.text)
        res.text = str
    }

    fun boolToText(check: Boolean): String {
        return when(check) {
            true -> "On"
            else -> "Off"
        }
    }
}