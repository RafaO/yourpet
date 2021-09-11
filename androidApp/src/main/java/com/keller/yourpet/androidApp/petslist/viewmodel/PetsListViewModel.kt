package com.keller.yourpet.androidApp.petslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keller.yourpet.mobilemain.usecase.FlowableUseCase.Result
import com.keller.yourpet.mobilemain.usecase.GetPetsUseCase
import com.keller.yourpet.mobilemain.usecase.invoke
import com.keller.yourpet.shared.model.Pet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetsListViewModel @Inject constructor(private val getPetsUseCase: GetPetsUseCase) :
    ViewModel() {

    // Observables

    val state: LiveData<PetsListViewState>
        get() = _state
    private val _state = MutableLiveData<PetsListViewState>()

    fun onViewRefreshed() = viewModelScope.launch {
        _state.postValue(PetsListViewState.Loading)
        getPetsUseCase().collect {
            when (it) {
                is Result.Success -> _state.postValue(PetsListViewState.Content(it.result))
                is Result.Failure -> errorReceived(it)
            }
        }
    }

    private fun errorReceived(it: Result.Failure<List<Pet>>) {
        if (_state.value !is PetsListViewState.Content)
            _state.postValue(
                PetsListViewState.Error(it.error?.message ?: "Something went wrong")
            )
    }
}
