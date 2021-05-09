package com.keller.yourpet.androidApp.petslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.keller.yourpet.shared.common.usecase.FlowableUseCase.Result
import com.keller.yourpet.shared.common.usecase.invoke
import com.keller.yourpet.shared.usecase.GetPetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class PetsListViewModel @Inject constructor(getPetsUseCase: GetPetsUseCase) : ViewModel() {

    // Observables

    val state = liveData {
        getPetsUseCase().collect {
            when (it) {
                is Result.Success -> emit(PetsListViewState.Content(it.result))
                is Result.Failure -> emit(
                    PetsListViewState.Error(
                        it.error?.message ?: "Something went wrong"
                    )
                )
            }
        }
    }
}
