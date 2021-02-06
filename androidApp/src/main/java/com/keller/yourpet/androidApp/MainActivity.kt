package com.keller.yourpet.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.keller.yourpet.shared.Greeting
import android.widget.TextView
import com.keller.yourpet.shared.usecase.GetPetsUseCase

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = GetPetsUseCase().execute().toString()
    }
}
