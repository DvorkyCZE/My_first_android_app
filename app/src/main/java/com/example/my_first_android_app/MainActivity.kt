package com.example.my_first_android_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var answer = 0
    var isGameOver = false
    var numOfAttempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startOver()
    }

    fun generateAnswer() {
        answer = Random.nextInt(1,25)
    }

    fun startOver() {

        isGameOver = false
        generateAnswer()

        numOfAttempts = 0

        val answerTextView = findViewById<TextView>(R.id.answer)
        answerTextView.text = "??"

        val submitButton = findViewById<Button>(R.id.buttonSubmit)
        submitButton.isEnabled = true

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Guess 1 to 25"

        val startOverButton = findViewById<Button>(R.id.buttonStartOver)
        startOverButton.isEnabled = false

        val editTextGuess = findViewById<EditText>(R.id.editTextGuess)
        editTextGuess.text.clear()
    }

    fun btnStartOverTapped() {
        startOver()
    }

    fun btnSubmitTapped() {

        val guess = getUserGuess() ?: -999

        val textView = findViewById<TextView>(R.id.textView)

        if (guess !in 1..25){
            textView.text = "Guess must be 1 to 25!"
            return
        }

        var message = ""
        numOfAttempts++
        if (guess == answer){
            message = "Correct! Guess(es): $numOfAttempts"
            isGameOver = true

            val answerTextView = findViewById<TextView>(R.id.answer)
            answerTextView.text = answer.toString()

            val submitButton = findViewById<Button>(R.id.buttonSubmit)
            submitButton.isEnabled = false

            val startOverButton = findViewById<Button>(R.id.buttonStartOver)
            startOverButton.isEnabled = true

        }else {
            message = if (guess < answer) "Too low!" else "Too high!"
        }

        textView.text = message
    }

    fun getUserGuess() : Int? {
        val editTextGuess = findViewById<EditText>(R.id.editTextGuess)
        val usersGuess = editTextGuess.text.toString()

        var guessAsInt = 0

        try {
            guessAsInt = usersGuess.toInt()
        } catch (e: Exception){
            return null
        }

        return guessAsInt
    }

}