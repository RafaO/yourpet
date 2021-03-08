package com.keller.yourpet.androidApp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.keller.yourpet.shared.api.PetsApiClient
import com.keller.yourpet.shared.database.PetsDataBase
import com.keller.yourpet.shared.repository.PetsRepository
import com.keller.yourpet.shared.usecase.GetPetsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)

        // TODO don't use global scope
        GlobalScope.launch {
            GetPetsUseCase(PetsRepository(PetsDataBase(), PetsApiClient())).execute()
                .map { it.toString() }
                .collect {
                    withContext(Dispatchers.Main) {
                        tv.text = it
                    }
                }
        }
    }
}
