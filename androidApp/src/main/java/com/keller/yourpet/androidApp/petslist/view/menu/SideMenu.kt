package com.keller.yourpet.androidApp.petslist.view.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.keller.yourpet.shared.model.Gender

@Composable
fun SideMenu(onGenderSelected: (Gender, Boolean) -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text("Filters")
        for (gender in Gender.values())
            LabelledCheckbox(gender, onGenderSelected)
    }
}

@Composable
fun LabelledCheckbox(gender: Gender, onGenderSelected: (Gender, Boolean) -> Unit) {
    val isChecked = remember { mutableStateOf(true) }

    Row {
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = {
                isChecked.value = it
                onGenderSelected(gender, it)
            },
        )
        Text(text = gender.toString())
    }
}
