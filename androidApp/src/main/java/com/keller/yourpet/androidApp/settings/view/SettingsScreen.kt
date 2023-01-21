package com.keller.yourpet.androidApp.settings.view

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keller.yourpet.androidApp.R
import com.keller.yourpet.androidApp.main.viewmodel.MainViewModel
import com.keller.yourpet.androidApp.main.viewmodel.ThemeColor
import com.keller.yourpet.androidApp.ui.UIGroups

@Composable
fun SettingsScreen() {
    val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
    var themeMenuExpanded by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.text_padding))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Theme")
            Box {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = uiState.themeColor.toString())
                    IconButton(onClick = { themeMenuExpanded = true }) {
                        Icon(Icons.Default.Edit, contentDescription = "Theme")
                    }
                }
                DropdownMenu(
                    expanded = themeMenuExpanded,
                    onDismissRequest = { themeMenuExpanded = false },
                    properties = PopupProperties(focusable = true),
                ) {
                    ThemeColor.values().forEach {
                        DropdownMenuItem(
                            onClick = {
                                mainViewModel.onThemeColorSelected(it)
                                themeMenuExpanded = false
                            },
                        ) {
                            Text(text = it.name)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "side menu", group = UIGroups.GeneralElements)
@Composable
fun DefaultPreview() {
    SettingsScreen()
}
