package com.keller.yourpet.androidApp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.keller.yourpet.androidApp.ui.YourPetUITheme
import com.keller.yourpet.shared.api.PetsApiClient
import com.keller.yourpet.shared.database.DatabaseDriverFactory
import com.keller.yourpet.shared.database.DatabaseModule
import com.keller.yourpet.shared.database.PetsDataBaseHelper
import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.repository.PetsRepository
import com.keller.yourpet.shared.usecase.GetPetsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val databaseDriverFactory = DatabaseDriverFactory(this)
        val database = DatabaseModule().createDataBase(databaseDriverFactory.createDriver())

        setContent {
            YourPetUITheme {
                val scope = rememberCoroutineScope()
                val petsList = mutableStateOf(emptyList<Pet>())
                scope.launch {
                    GetPetsUseCase(
                        PetsRepository(
                            PetsDataBaseHelper(database),
                            PetsApiClient()
                        )
                    ).execute().collect {
                        petsList.value = it
                    }
                }
                Surface(color = MaterialTheme.colors.background) { PetsNames(pets = petsList.value) }
            }
        }
    }
}

@Composable
fun PetsNames(pets: List<Pet>) {
    Text(text = pets.toString())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YourPetUITheme {
        PetsNames(listOf(Pet("charlie")))
    }
}
