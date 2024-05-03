package com.example.myapplication

enum class BeerType{LIGHT_LAGER, LIGHT_HYBRID, BOCK, AMBER_HYBRID, FRUIT}
enum class WineType{WHITE, ROSE, RED, SPARKLING, DESSERT}
class Beer(var name:String, var beerType:BeerType,var cost:Int)
class Wine(var name:String, var wineType:WineType,var cost:Int)

fun Beer.print(){
    println("맥주이름 : $name , 맥주타입 : ${beerType}, 맥주가격 : $cost")
}
fun Beer.change_price(price:Int){
    cost = price
}

fun Wine.euro_to_won(operation:(Int,Int)->Int):Int{
    var won = operation(1350,cost)
    println("유로 : $cost , 원화 : $won")
    return operation(1300,cost)
}

fun main() {

    var beer1 = Beer("Hite",BeerType.FRUIT,200)
    var beer2 = Beer("Cass",BeerType.LIGHT_HYBRID,200)

    beer1.change_price(600)
    beer1.print()
    beer2.change_price(600)
    beer2.print()

    var wine1 = Wine("Cabernet",WineType.RED,10)
    var wine2 = Wine("Chardonnay",WineType.WHITE,12)

    wine1.euro_to_won { a,b->
        a*b
    }

}

