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

@Composable
fun SideMenu() {
    Column(modifier = Modifier.padding(8.dp)) {
        Text("Filters")
        LabelledCheckbox("male")
        LabelledCheckbox("female")
    }
}

@Composable
fun LabelledCheckbox(label: String) {
    Row {
        val isChecked = remember { mutableStateOf(true) }

        Checkbox(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it },
        )
        Text(text = label)
    }
}
