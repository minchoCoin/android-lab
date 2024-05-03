package com.example.myapplication

fun Year(y:Int):Int{
    if (y % 400 == 0) {
        return 1
    }
    if (y%100 ==0){
        return 0
    }
    if (y%4==0){
        return 1
    }
    return 0
}
fun main(){
    println("2000년은 윤년일까?")
    println(Year(2000))

    println("1900년은 윤년일까?")
    println(Year(1900))

    println("2020년은 윤년일까?")
    println(Year(2020))

    println("2013년은 윤년일까?")
    println(Year(2013))
}