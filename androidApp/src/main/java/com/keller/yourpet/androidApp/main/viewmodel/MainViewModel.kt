package com.keller.yourpet.androidApp.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * ViewModel that hosts state affecting the whole application.
 * Use activity scope to retrieve the single instance of it.
 * See [com.keller.yourpet.androidApp.main.view.MainActivity] as an example.
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    companion object {
        private val initialState = MainViewState(ThemeColor.SYSTEM)
    }

    private val _uiState: MutableStateFlow<MainViewState> = MutableStateFlow(initialState)
    val uiState = _uiState.stateIn(
        viewModelScope,
        initialValue = initialState,
        started = WhileSubscribed(5000),
    )

    fun onThemeColorSelected(selectedColor: ThemeColor) {
        _uiState.update { it.copy(themeColor = selectedColor) }
    }
}
