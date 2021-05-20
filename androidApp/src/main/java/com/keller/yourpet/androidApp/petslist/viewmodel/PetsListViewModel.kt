package com.keller.yourpet.androidApp.petslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keller.yourpet.shared.common.usecase.FlowableUseCase.Result
import com.keller.yourpet.shared.common.usecase.invoke
import com.keller.yourpet.shared.usecase.GetPetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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
                is Result.Failure -> _state.postValue(
                    PetsListViewState.Error(
                        it.error?.message ?: "Something went wrong"
                    )
                )
            }
        }
    }
}
