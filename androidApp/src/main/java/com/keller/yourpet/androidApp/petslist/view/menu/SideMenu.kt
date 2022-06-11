package com.keller.yourpet.androidApp.petslist.view.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.android.showkase.models.Showkase
import com.keller.yourpet.androidApp.BuildConfig
import com.keller.yourpet.androidApp.R
import com.keller.yourpet.androidApp.ui.UIGroups
import com.keller.yourpet.androidApp.ui.YourPetUITheme
import com.keller.yourpet.androidApp.ui.getBrowserIntent
import com.keller.yourpet.shared.model.Gender

@Composable
fun MenuItem(text: String, onClick: () -> Unit) {
    Text(
        text,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .height(dimensionResource(R.dimen.height_clickable))
            .padding(start = dimensionResource(R.dimen.text_padding))
            .wrapContentHeight(Alignment.CenterVertically)
    )
}

@Composable
fun SideMenu(onGenderSelected: (Gender, Boolean) -> Unit) {
    val context = LocalContext.current

    Column(modifier = Modifier.padding(8.dp)) {
        Text("Filters")
        for (gender in Gender.values())
            LabelledCheckbox(gender, onGenderSelected)

        MenuItem("Settings") {}

        Spacer(modifier = Modifier.weight(1.0f))
        if (BuildConfig.DEBUG) {
            Button(onClick = {
                context.startActivity(Showkase.getBrowserIntent(context))
            }) {
                Text("Showkase browser")
            }
        }
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

@Preview(showBackground = true, name = "side menu", group = UIGroups.GeneralElements)
@Composable
fun DefaultPreview() {
    YourPetUITheme {
        SideMenu { _, _ -> }
    }
}
