package com.keller.yourpet.androidApp.main.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * ViewModel that hosts state affecting the whole application.
 * Use activity scope to retrieve the single instance of it.
 * See [com.keller.yourpet.androidApp.main.view.MainActivity] as an example.
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<MainViewState> =
        MutableStateFlow(MainViewState(ThemeColor.SYSTEM))
    val uiState = _uiState.asStateFlow()

    fun onThemeColorSelected(selectedColor: ThemeColor) {
        _uiState.update { it.copy(themeColor = selectedColor) }
    }
}
