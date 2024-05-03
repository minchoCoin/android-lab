package com.example.myapplication

class Calculator() {
    private var calculated_values = emptyArray<Double>()
    private var calculated_value = 0.0
    private fun add(a:Double,b:Double){
        calculated_value=a+b
        calculated_values=calculated_values.plus(calculated_value)
    }
    private fun subtract(a:Double,b:Double){
        calculated_value=a-b
        calculated_values=calculated_values.plus(calculated_value)
    }
    private fun multiple(a:Double,b:Double){
        calculated_value=a*b
        calculated_values=calculated_values.plus(calculated_value)
    }
    private fun division(a:Double,b:Double){
        if ( b==0.0 ){
            println("ERROR : number can not be divided with zero.")
        }
        else{
            calculated_value= a / b
            calculated_values=calculated_values.plus(calculated_value)
        }
    }
    fun load() {
        for (i in calculated_values){
            println(i)
        }
    }
    fun cal(a:(Double),b:(Double), type:String){
        when (type){
            "add"->{
                add(a,b)
            }
            "subtract"->{
                subtract(a,b)
            }
            "multiple"->{
                multiple(a,b)
            }
            "division"->{
                division(a,b)
            }
        }
    }
    fun print_calculated_number(){
        println(calculated_value)
    }
}
fun main(){
    var calculator = Calculator()
    calculator.cal(1.0,2.0,"add")
    calculator.print_calculated_number()
    calculator.cal(1.0,10.0,"subtract")
    calculator.print_calculated_number()
    calculator.cal(1.0,0.0,"division")
    calculator.print_calculated_number()
    calculator.cal(1.0,10.0,"division")
    calculator.print_calculated_number()
    calculator.cal(1.0,10.0,"multiple")
    calculator.print_calculated_number()
    println("----------------")
    calculator.load()
}

//    val user = User()
//    user.sayHello()
//
//    println(formatData("2"))