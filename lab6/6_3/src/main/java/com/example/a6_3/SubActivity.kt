package com.example.a6_3

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val name_age:TextView=findViewById(R.id.name_age_sub)
        val phone:TextView=findViewById(R.id.phone_sub)
        val address:TextView=findViewById(R.id.address_sub)
        val etc:TextView=findViewById(R.id.etc_sub)
        val image:ImageView=findViewById(R.id.image_sub)
        val btn_change:Button=findViewById(R.id.change_sub)
        val btn_return:Button=findViewById(R.id.return_sub)

        name_age.text=intent.getStringExtra("name")+"\n"+intent.getStringExtra("age")
        phone.text=intent.getStringExtra("phone")
        address.text=intent.getStringExtra("address")
        etc.text=intent.getStringExtra("etc")
        val bitmap = intent.getParcelableExtra<Bitmap>("image")
        image.setImageBitmap(bitmap)

        btn_return.setOnClickListener {

            setResult(RESULT_OK)
            finish()
        }

        btn_change.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }



    }
}