package com.keller.yourpet.androidApp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keller.yourpet.androidApp.ui.YourPetUITheme
import com.keller.yourpet.androidApp.viewmodel.PetsListViewModel
import com.keller.yourpet.androidApp.viewmodel.ViewModelFactory
import com.keller.yourpet.shared.data.MyDatabase
import com.keller.yourpet.shared.database.DatabaseDriverFactory
import com.keller.yourpet.shared.database.DatabaseModule
import com.keller.yourpet.shared.model.Pet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val databaseDriverFactory = DatabaseDriverFactory(this)
        val database = DatabaseModule().createDataBase(databaseDriverFactory.createDriver())

        setContent {
            YourPetUITheme {
                AppsHome(database)
            }
        }
    }
}

@Composable
fun AppsHome(
    database: MyDatabase,
    viewModel: PetsListViewModel = viewModel(factory = ViewModelFactory(database))
) {
    val petsList by viewModel.pets.observeAsState(emptyList())
    Surface(color = MaterialTheme.colors.background) {
        PetsNames(pets = petsList)
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
