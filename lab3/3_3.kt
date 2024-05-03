package com.example.myapplication


fun change(a:String):String{
    var islower: Boolean = true
    var notlower = ""
    for (i:Int in 0..a.length-1 step(1)){
        if( 97 <= a[i].toInt() && a[i].toInt() <= 122) {
            continue
        }else{
            notlower += a[i]
            islower = false
        }
    }
    if(islower) return a.toUpperCase()
    return "error with = "+notlower
}



fun main(){
    var a = "abcd"
    println(change(a))

    var b = "EfgH"
    println(change(b))

    var c = "!@%$"
    println(change(c))
}