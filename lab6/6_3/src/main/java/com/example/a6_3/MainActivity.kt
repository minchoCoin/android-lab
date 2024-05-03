package com.example.a6_3

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Gallery
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.drawToBitmap

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.name_main)
        val age = findViewById<EditText>(R.id.age_main)
        val phone = findViewById<EditText>(R.id.phone_main)
        val address = findViewById<EditText>(R.id.address_main)
        val etc = findViewById<EditText>(R.id.etc_main)
        val image:ImageView=findViewById(R.id.image_main)
        image.isClickable = true
        val btn:Button=findViewById(R.id.save_main)

        image.setOnClickListener{
            openGallery()
        }

        btn.setOnClickListener {
            val intent= Intent(this,SubActivity::class.java)
            intent.putExtra("name",name.text.toString())
            intent.putExtra("age",age.text.toString())
            intent.putExtra("phone",phone.text.toString())
            intent.putExtra("address",address.text.toString())
            intent.putExtra("etc",etc.text.toString())
            val resize=resizeBitmap(image.drawToBitmap(),300,300)
            intent.putExtra("image",resize)
            startActivityForResult(intent,100)
        }

    }
    private fun openGallery(){
        val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent,1)
    }

    private fun resizeBitmap(bitmap: Bitmap,width:Int,height:Int):Bitmap{
        return Bitmap.createScaledBitmap(bitmap,width,height,false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK){
            when(requestCode){
                1 ->{
                    var ImageData: Uri?=data?.data
                    try{
                        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,ImageData)
                        val pt:ImageView=findViewById(R.id.image_main)
                        pt.setImageBitmap(bitmap)
                    }catch(e:Exception){
                        e.printStackTrace()
                    }
                }

                100 ->{
                    val name = findViewById<EditText>(R.id.name_main)
                    val age = findViewById<EditText>(R.id.age_main)
                    val phone = findViewById<EditText>(R.id.phone_main)
                    val address = findViewById<EditText>(R.id.address_main)
                    val etc = findViewById<EditText>(R.id.etc_main)
                    val image:ImageView=findViewById(R.id.image_main)
                    val intent= Intent(this,ResultActivity::class.java)
                    intent.putExtra("name",name.text.toString())
                    intent.putExtra("age",age.text.toString())
                    intent.putExtra("phone",phone.text.toString())
                    intent.putExtra("address",address.text.toString())
                    intent.putExtra("etc",etc.text.toString())
                    val resize=resizeBitmap(image.drawToBitmap(),300,300)
                    intent.putExtra("image",resize)
                    startActivity(intent)
                    name.text=null
                    age.text=null
                    phone.text=null
                    address.text=null
                    etc.text=null
                    image.setImageResource(0)
                }

            }
        }

    }
}