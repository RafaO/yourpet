package com.keller.yourpet.androidApp.petslist.viewmodel

import com.keller.yourpet.androidApp.utils.CoroutinesTest
import com.keller.yourpet.androidApp.utils.getOrAwaitValue
import com.keller.yourpet.shared.common.usecase.FlowableUseCase.Result
import com.keller.yourpet.shared.common.usecase.invoke
import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.usecase.GetPetsUseCase
import com.keller.yourpet.shared.wrap
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Test

@ExperimentalCoroutinesApi
class PetsListViewModelTests : CoroutinesTest() {
    @Test
    fun `delivers corresponding state`() {
        // given
        val useCase = mockk<GetPetsUseCase>()
        coEvery { useCase() } returns flow<Result<List<Pet>>> { emit(Result.Success(emptyList())) }.wrap()
        val subject = PetsListViewModel(useCase)

        // when
        val result = subject.state.getOrAwaitValue()

        // then
        assertEquals(PetsListViewState.Content(emptyList()), result)
    }
}
