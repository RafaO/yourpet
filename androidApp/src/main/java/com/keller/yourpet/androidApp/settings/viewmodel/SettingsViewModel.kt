package com.keller.yourpet.androidApp.settings.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<SettingsViewState> =
        MutableStateFlow(SettingsViewState(ThemeColor.SYSTEM))
    val uiState = _uiState.asStateFlow()

    fun onThemeColorSelected(selectedColor: ThemeColor) {
        _uiState.update { it.copy(themeColor = selectedColor) }
    }
}
