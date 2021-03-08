package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var insertedMass = 0;
    private var insertedHeight = 0;
    private var BMIValue = 0.0;
    private var metric = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.metric_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.metric -> {
                metric = true
                findViewById<TextView>(R.id.textView).text = getString(R.string.height_metric)
                findViewById<TextView>(R.id.textView2).text = getString(R.string.mass_metric)
                true
            }
            R.id.imperial -> {
                metric = false
                findViewById<TextView>(R.id.textView).text = getString(R.string.height_imperial)
                findViewById<TextView>(R.id.textView2).text = getString(R.string.mass_imperial)

                true
            }
            R.id.author -> {
                val intent = Intent(this, Author::class.java).apply {
                }
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun calculate(view: View) {

        val mass_editText = findViewById<EditText>(R.id.editTextNumber)
        val height_editText = findViewById<EditText>(R.id.editTextNumber2)
        if (mass_editText.text.isNullOrBlank() || height_editText.text.isNullOrBlank())
        {
            insertedMass = 0
            insertedHeight = 0
        }
        else {
            insertedMass = mass_editText.text.toString().toInt()
            insertedHeight = height_editText.text.toString().toInt()
        }
        BMIValue = BMICounter().calculate(insertedHeight, insertedMass, metric)

        if (BMIValue != 0.0) {
            var color = BMICounter().classify(insertedHeight, insertedMass, metric)
            findViewById<TextView>(R.id.textView3).text = BMIValue.toString()
            findViewById<TextView>(R.id.textView3).setBackgroundColor(ContextCompat.getColor(this, color))
            findViewById<TextView>(R.id.textView3).setTextColor(ContextCompat.getColor(this, R.color.white))

        }
        else {
            findViewById<TextView>(R.id.textView3).text = "Wtf man"
            findViewById<TextView>(R.id.textView3).setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            findViewById<TextView>(R.id.textView3).setTextColor(ContextCompat.getColor(this, R.color.black))
        }
    }

}