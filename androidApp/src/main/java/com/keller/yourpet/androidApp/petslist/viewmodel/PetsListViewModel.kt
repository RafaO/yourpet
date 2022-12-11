package com.keller.yourpet.androidApp.petslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keller.yourpet.mobilemain.usecase.BaseFlowableUseCase.Result
import com.keller.yourpet.mobilemain.usecase.GetPetsUseCase
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Pet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetsListViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase,
    private val filters: Filter,
) : ViewModel() {

    companion object {
        private val initialValue = PetsListViewState.Content(emptyList())
    }

    // Observables

    private val _uiState: MutableStateFlow<PetsListViewState> = MutableStateFlow(initialValue)
    val uiState = _uiState.stateIn(
        viewModelScope,
        WhileSubscribed(5000),
        initialValue
    )

    fun onViewRefreshed() = viewModelScope.launch {
        _uiState.update { PetsListViewState.Loading }
        getPetsUseCase(filters).onEach { result ->
            when (result) {
                is Result.Success ->
                    _uiState.update { PetsListViewState.Content(result.result) }
                is Result.Failure -> errorReceived(result)
            }
        }.collect()
    }

    private fun errorReceived(error: Result.Failure<List<Pet>>) {
        if (_uiState.value !is PetsListViewState.Content)
            _uiState.update {
                PetsListViewState.Error(error.error?.message ?: "Something went wrong")
            }
    }
}
