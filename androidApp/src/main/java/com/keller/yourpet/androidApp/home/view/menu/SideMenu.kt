package com.keller.yourpet.androidApp.home.view.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.airbnb.android.showkase.models.Showkase
import com.keller.yourpet.androidApp.BuildConfig
import com.keller.yourpet.androidApp.R
import com.keller.yourpet.androidApp.ui.UIGroups
import com.keller.yourpet.androidApp.ui.YourPetUITheme
import com.keller.yourpet.androidApp.ui.getBrowserIntent
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Gender

@Composable
fun MenuItem(option: MenuOption, onOptionSelected: (MenuOption) -> Unit) {
    Text(
        option.text,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOptionSelected(option) }
            .height(dimensionResource(R.dimen.height_clickable))
            .padding(start = dimensionResource(R.dimen.text_padding))
            .wrapContentHeight(Alignment.CenterVertically)
    )
}

@Composable
fun SideMenu(
    filter: Filter,
    options: List<MenuOption>,
    selectedOption: MenuOption,
    onGenderSelected: (Gender, Boolean) -> Unit,
    onOptionSelected: (MenuOption) -> Unit,
) {
    val context = LocalContext.current

    Surface(color = MaterialTheme.colorScheme.surfaceVariant) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            for (option in options) {
                MenuItem(option = option, onOptionSelected = onOptionSelected)
                if (option is MenuOption.Pets && selectedOption is MenuOption.Pets) {
                    Text("Filters")
                    for (gender in Gender.values())
                        LabelledCheckbox(filter, gender, onGenderSelected)
                }
            }

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
}

@Composable
fun LabelledCheckbox(filter: Filter, gender: Gender, onGenderSelected: (Gender, Boolean) -> Unit) {
    val isChecked = remember { mutableStateOf(filter.genders.contains(gender)) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = {
                isChecked.value = it
                onGenderSelected(gender, it)
            },
        )
        Text(
            modifier = Modifier.wrapContentHeight(Alignment.CenterVertically),
            text = gender.toString()
        )
    }
}

@Preview(showBackground = true, name = "side menu", group = UIGroups.GeneralElements)
@Composable
fun DefaultPreview() {
    YourPetUITheme {
        SideMenu(
            Filter(),
            listOf(MenuOption.Pets(rememberNavController(), false) {}, MenuOption.Settings()),
            MenuOption.Pets(rememberNavController(), false) {},
            { _, _ -> },
            {})
    }
}
