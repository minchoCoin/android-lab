package org.techtown.myfile

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings.Global.putInt
import android.provider.Settings.Global.putString
import android.util.Log
import androidx.core.content.FileProvider
import java.io.*
import java.nio.Buffer
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //File 클래스 이용 입출력 - 피피티 19페이지 부터 시작
        val file = File(filesDir,"test.txt")
        val writeStream: OutputStreamWriter = file.writer()
        writeStream.write("hello world")
        writeStream.flush()

        val readStrem: BufferedReader = file.reader().buffered()
        readStrem.forEachLine {
            Log.d("kkang","$it")
        }

        /* Context 객체 제공 함수 이용
        openFileOutput("test.txt",
            Context.MODE_PRIVATE).use{
            it.write("hello world!!".toByteArray())
        }
        openFileInput("test.txt")
            .bufferedReader().forEachLine {
            Log.d("kkang","$it")
        }
        */

        /* 외장 메모리 사용 가능한지에 대한 코드
        if(Environment.getExternalStorageState()
            == Environment.MEDIA_MOUNTED){
            Log.d("kkangg","ExternalStorageState Mounted")
        }else{
            Log.d("kkangg","ExternalStorageState UnMounted")
        }
        */

        /* 외장메모리 파일 입출력
        val file: File = File(getExternalFilesDir(null),
           "test.txt")
       val writeStream: OutputStreamWriter = file.writer()
       writeStream.write("hello world")
       writeStream.flush()

       val readStream: BufferedReader =
           file.reader().buffered()
       readStream.forEachLine {
           Log.d("kkang","$it")
       }
       */

        /* SharedPreferences 이용한 데이터 관리
        //val sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val sharedPref = getPreferences(Context.MODE_PRIVATE)

        sharedPref.edit().run{
            putString("data1","hello")
            putInt("data2",10)
            commit()
        }

        val data1 = sharedPref.getString("data1","world")
        val data2 = sharedPref.getInt("data2",10)

        Log.d("kkang", "${data1}, ${data2}")
        */

    }
}