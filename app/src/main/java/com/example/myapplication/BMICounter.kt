package com.example.myapplication

class BMICounter {

    private var BMIclassification = 0
    private var BMIvalue = 0.0

    fun  calculate(height: Int, mass: Int, metric: Boolean) : Double {

        var multiplier = 1
        if (!metric) multiplier = 703

        return if (height < 1 || mass < 1 || height > 300) {
            BMIvalue = 0.0
            0.0
        } else {
            BMIvalue = (mass.toDouble() / (height.toDouble() * height.toDouble())) * 10000 * multiplier
            BMIvalue
        }

    }

    fun classify (height: Int, mass: Int, metric: Boolean) : Int {

        BMIvalue = calculate(height, mass, metric)

        if (BMIvalue <= 0.0) BMIclassification = R.color.white
        else if (BMIvalue < 18.5) BMIclassification = R.color.underweight
        else if (BMIvalue < 24.99) BMIclassification = R.color.normal
        else if (BMIvalue < 29.99) BMIclassification = R.color.overweight
        else BMIclassification = R.color.obese
        return BMIclassification
    }

}