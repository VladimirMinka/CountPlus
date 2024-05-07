package com.example.countplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var txtView: TextView
    private lateinit var btnClick: Button
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtView = findViewById(R.id.count)
        btnClick = findViewById(R.id.btnPlus)

        btnClick.setOnClickListener {
            txtView.text = count.toString()
            count++
        }
    }

    //переопределяем методы-onSaveInstanceState/onRestoreInstanceState
    // сохраняем состояние
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("key", txtView.text.toString())
    }

    //восстанавливаем состояние
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        txtView.text = savedInstanceState.getString("key")
    }
}