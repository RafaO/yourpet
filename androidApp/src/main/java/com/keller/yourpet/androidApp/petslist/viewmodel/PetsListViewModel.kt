package com.keller.yourpet.androidApp.petslist.viewmodel

import androidx.compose.material.DrawerState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keller.yourpet.mobilemain.usecase.FlowableUseCase.Result
import com.keller.yourpet.mobilemain.usecase.GetPetsUseCase
import com.keller.yourpet.mobilemain.usecase.invoke
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetsListViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase,
    private val filters: Filter = Filter()
) : ViewModel() {

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

    suspend fun onFiltersClicked(drawerState: DrawerState) {
        if (drawerState.isClosed)
            drawerState.open()
        else
            drawerState.close()
    }

    fun onGenderSelected(gender: Gender, selected: Boolean) {
        if (selected) {
            filters.genders.add(gender)
        } else {
            filters.genders.remove(gender)
        }
    }

    private fun errorReceived(it: Result.Failure<List<Pet>>) {
        if (_state.value !is PetsListViewState.Content)
            _state.postValue(
                PetsListViewState.Error(it.error?.message ?: "Something went wrong")
            )
    }
}
