package com.example.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mycalculator.R

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var display: TextView
    private var state: Int = 1
    private var op: Int = 0
    private var op1: Double = 0.0
    private var op2: Double = 0.0
    private val expression = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        display = findViewById(R.id.text_result)

        // Set listeners for buttons
        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)
        findViewById<Button>(R.id.btnAdd).setOnClickListener(this)
        findViewById<Button>(R.id.btnSub).setOnClickListener(this)
        findViewById<Button>(R.id.btnMultiple).setOnClickListener(this)
        findViewById<Button>(R.id.btnDivide).setOnClickListener(this)
        findViewById<Button>(R.id.btnEqual).setOnClickListener(this)
        findViewById<Button>(R.id.btnCE).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val id = view?.id
        when (id) {
            R.id.btn0 -> addDigit(0)
            R.id.btn1 -> addDigit(1)
            R.id.btn2 -> addDigit(2)
            R.id.btn3 -> addDigit(3)
            R.id.btn4 -> addDigit(4)
            R.id.btn5 -> addDigit(5)
            R.id.btn6 -> addDigit(6)
            R.id.btn7 -> addDigit(7)
            R.id.btn8 -> addDigit(8)
            R.id.btn9 -> addDigit(9)

            R.id.btnAdd -> selectOperation(1, "+")
            R.id.btnSub -> selectOperation(2, "-")
            R.id.btnMultiple -> selectOperation(3, "x")
            R.id.btnDivide -> selectOperation(4, "/")

            R.id.btnEqual -> calculateResult()
            R.id.btnCE -> resetCalculator() // To clear input
        }
    }

    private fun addDigit(digit: Int) {
        if (state == 1) {
            op1 = op1 * 10 + digit
        } else {
            op2 = op2 * 10 + digit
        }
        expression.append(digit)
        display.text = expression.toString()
    }

    private fun selectOperation(operation: Int, symbol: String) {
        if (state == 1) {
            state = 2
            op = operation
            expression.append(" $symbol ")
            display.text = expression.toString()
        }
    }

    private fun calculateResult() {
        val result = when (op) {
            1 -> op1 + op2
            2 -> op1 - op2
            3 -> op1 * op2
            4 -> if (op2 != 0.0) op1 / op2 else {
                display.text = "Error" // Handle divide by zero
                return
            }
            else -> 0.0
        }
        expression.append(" = $result")
        display.text = expression.toString()
        resetCalculator(result)
    }

    private fun resetCalculator(result: Double = 0.0) {
        state = 1
        op1 = result
        op2 = 0.0
        op = 0
        expression.clear()
        if (result != 0.0) {
            expression.append(result)
        }
        display.text = if (result == 0.0) "" else result.toString()
    }
}
