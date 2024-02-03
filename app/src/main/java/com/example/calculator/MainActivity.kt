package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.app.Activity

class MainActivity : Activity() {

    private lateinit var resultEditText: EditText
    private val inputStringBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        resultEditText = findViewById(R.id.resultEditText)
    }

    fun onButtonClick(view: View) {
        val button = view as Button
        inputStringBuilder.append(button.text.toString())
        updateResult()
    }

    fun onClearButtonClick(view: View) {
        inputStringBuilder.clear()
        updateResult()
    }

    fun onCalculateButtonClick(view: View) {
        try {
            val result = evaluateExpression(inputStringBuilder.toString())
            inputStringBuilder.clear()
            inputStringBuilder.append(result)
            updateResult()
        } catch (e: Exception) {
            inputStringBuilder.clear()
            inputStringBuilder.append("Error")
            updateResult()
        }
    }

    private fun updateResult() {
        resultEditText.setText(inputStringBuilder.toString())
    }

    private fun evaluateExpression(expression: String): Double {
        val operators = listOf("+", "-", "*", "/")
        var currentNumber = ""
        var currentOperator = ""
        var result = 0.0

        for (char in expression) {
            val charStr = char.toString()

            if (charStr in "0123456789.") {
                currentNumber += charStr
            } else if (charStr in operators) {
                if (currentNumber.isNotEmpty()) {
                    val operand = currentNumber.toDouble()

                    when (currentOperator) {
                        "+" -> result += operand
                        "-" -> result -= operand
                        "*" -> result *= operand
                        "/" -> result /= operand
                        else -> result = operand
                    }

                    currentNumber = ""
                }

                currentOperator = charStr
            }
        }

        if (currentNumber.isNotEmpty()) {
            val operand = currentNumber.toDouble()

            when (currentOperator) {
                "+" -> result += operand
                "-" -> result -= operand
                "*" -> result *= operand
                "/" -> result /= operand
                else -> result = operand
            }
        }

        return result
    }
}
