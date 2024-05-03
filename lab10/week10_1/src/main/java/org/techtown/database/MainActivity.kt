package org.techtown.database

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val databaseName = "people"
    var database: SQLiteDatabase? = null

    val tableName = "student"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 저장소 생성, 테이블 생성, 데이터 추가, 데이터 조회에 대한 버튼 이벤트와 함수들
        doButton1.setOnClickListener{
            createDatabase()
        }

        doButton2.setOnClickListener{
            createTable()
        }

        doButton3.setOnClickListener{
            addData()
        }

        doButton4.setOnClickListener{
            queryData()
        }


    }

    fun createDatabase(){
        database = openOrCreateDatabase(databaseName,
            MODE_PRIVATE, null)
        output1.append("데이터베이스 생성 또는 오픈함\n")
    }

    fun createTable(){
        val sql = "create table if not exists ${tableName}" +
                "(_id integer PRIMARY KEY autoincrement, " +
                "name text, " +
                "age integer, " +
                "mobile text)"

        if(database == null){
            output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }

        database?.execSQL(sql)

        output1.append("테이블 생성함\n")
    }

    fun addData(){
        val sql = "insert into ${tableName}(name,age,mobile)"+
                "values"+
                "('john','20','010-0000-0000')"

        if (database == null){
            output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }

        database?.execSQL(sql)
        output1.append("데이터 추가함\n")
    }

    fun queryData(){
        val sql = "select _id,name,age,mobile from ${tableName}"

        if(database == null){
            output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }

        val cursor = database?.rawQuery(sql,null)
        if(cursor != null){
            for (index in 0 until cursor.count){
                cursor.moveToNext()
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val age = cursor.getInt(2)
                val mobile = cursor.getString(3)
                output1.append("레코드#${index} : " +
                        "$id,$name,$age,$mobile\n ")
            }
            cursor.close()

        }
        output1.append("데이터 조회 결과\n")
    }



}

/* 이 부분은 실습 18 페이지 관련 코드
        doButton1.setOnClickListener{
            InsertData()
        }

        doButton2.setOnClickListener{
            UpdateData()
        }

        doButton3.setOnClickListener{
            QueryData()
        }
 */

/* 위에 소스코드에 대한 함수들
fun InsertData(){
        val values = ContentValues()
        values.put("name", "mike")
        values.put("age", 24)
        values.put("mobile", "010-4000-4000")
        database?.insert(tableName, null, values)
        output1.append("데이터 추가함\n")
    }

    fun UpdateData(){
        val values = ContentValues()
        values.put("age", 26)
        val arr : Array<String> = arrayOf("mike")
        database?.update(tableName, values, "name=?", arr)
        output1.append("데이터 수정함\n")
    }

    fun QueryData(){
        val sql = "select _id,name,age,mobile from ${tableName}"

        if(database == null){
            output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }


        val cursor = database?.rawQuery("select _id,name,age,mobile " +
                "from ${tableName}",null)
        if(cursor != null){
            for (index in 0 until cursor.count){
                cursor.moveToNext()
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val age = cursor.getInt(2)
                val mobile = cursor.getString(3)
                output1.append("레코드#${index} : " +
                        "$id,$name,$age,$mobile\n ")
            }
            cursor.close()

        }
        output1.append("데이터 조회 결과\n")
    }
 */