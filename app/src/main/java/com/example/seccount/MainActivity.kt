package com.example.seccount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
private lateinit var button: Button
private lateinit var editText: EditText
private lateinit var timerCount:TextView
private var handler = Handler()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewFinders()
        changeButtonColor(1)



        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                timerCount.setTextColor(getColor(R.color.black))
                timerCount.setText(p0)
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val inputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
                inputMethodManager?.hideSoftInputFromWindow(editText.windowToken, 0)
                true
            } else false
        }
        button.setOnClickListener {
            var remainingSeconds = timerCount.text.toString().toInt()
            changeButtonColor(remainingSeconds)
            runTimer(remainingSeconds)

        }
            }

       fun runTimer(seconds: Int) {
            if (seconds > 0) {
                handler.postDelayed({
                    timerCount.text = (seconds - 1).toString()
                    runTimer(seconds - 1)
                }, 1000)
            }
           if (seconds<=0){
               timerCount.setTextColor(getColor(R.color.green))
               timerCount.text = "Done!"
           }
       }




    private fun viewFinders(){
         button = findViewById(R.id.startTimerButton)
         editText = findViewById(R.id.editText)
         timerCount = findViewById(R.id.secondsLeftTextView)

    }
    private fun changeButtonColor(remainingSeconds: Int){
        if (remainingSeconds == 0){
            button.setBackgroundColor(getColor(R.color.green))
        }
        else button.setBackgroundColor(getColor(R.color.purple_500))
    }

    }

