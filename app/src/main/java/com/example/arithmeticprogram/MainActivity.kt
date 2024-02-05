package com.example.arithmeticprogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Fields
        val fieldOne: EditText = findViewById(R.id.edittext1)
        val fieldTwo: EditText = findViewById(R.id.edittext2)
        val calculate: Button = findViewById(R.id.calcbutton)
        lateinit var selectedOp: String
        val answer: TextView = findViewById(R.id.answer)

        //string to hold the answer
        answer.text = ""

        //List of operations the calculator can perform
        val operations = listOf("Add", "Subtract", "Multiply", "Divide", "Modulus")

        // Get the array of options from resources
        val optionsArray = resources.getStringArray(R.array.options)

        //make array adapter from list of options
        val adapter = ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, optionsArray)


        //set the layout resource for the spinner items
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //find the spinner view in the layout
        val spinner = findViewById<Spinner>(R.id.spinner)

        //set the array adapter to the spinner
        spinner.adapter = adapter


        //Assign an onitemselected listener
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedOp = parent?.getItemAtPosition(position).toString()
            }
            //when nothing is selected
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }



        //onclick listener for the calculate button
        calculate.setOnClickListener { view: View ->

            //obtain all of the values and convert them to double
            val fieldOneVal:Double = fieldOne.text.toString().toDouble()
            val fieldTwoVal: Double = fieldTwo.text.toString().toDouble()

            //now perform the calculation
            if (selectedOp == "Add") {
                answer.text = (fieldOneVal + fieldTwoVal).toString()
            }
            else if (selectedOp == "Subtract") {
                answer.text = (fieldOneVal - fieldTwoVal).toString()
            }
            else if (selectedOp == "Multiply") {
                answer.text = (fieldOneVal * fieldTwoVal).toString()
            }
            else if (selectedOp == "Divide") {
                //First, check if we are dividing by zero
                if (fieldTwoVal == 0.0) {
                    answer.text = "Error! Cannot divide by zero."
                }
                else {
                    answer.text = (fieldOneVal / fieldTwoVal).toString()
                }
            }
            else {
                //again, check for modulus by zero
                if (fieldTwoVal == 0.0) {
                    answer.text = "Error! Cannot perform modulus by zero."
                }
                else {
                    answer.text = (fieldOneVal % fieldTwoVal).toString()
                }
            }
        }
    }
}