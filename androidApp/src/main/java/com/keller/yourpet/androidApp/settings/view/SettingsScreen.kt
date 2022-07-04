package com.keller.yourpet.androidApp.settings.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.keller.yourpet.androidApp.settings.viewmodel.SettingsViewModel
import com.keller.yourpet.androidApp.settings.viewmodel.ThemeColor
import com.keller.yourpet.androidApp.ui.UIGroups

@Composable
fun SettingsScreen() {
    val viewModel: SettingsViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()
    var themeMenuExpanded by remember { mutableStateOf(false) }

    Column {
        Row {
            Text("Theme")
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopEnd)
            ) {
                Row(modifier = Modifier) {
                    Text(text = state.themeColor.toString())
                    IconButton(onClick = { themeMenuExpanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Theme")
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
                                viewModel.onThemeColorSelected(it)
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
