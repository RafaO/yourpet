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
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@ExperimentalCoroutinesApi
@RunWith(Parameterized::class)
class ViewStateTests(
    private val useCaseResult: Result<List<Pet>>,
    private val viewState: PetsListViewState
) : CoroutinesTest() {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(Result.Success(emptyList<Pet>()), PetsListViewState.Content(emptyList())),
            arrayOf(
                Result.Success(listOf(Pet("Charlie", ""))),
                PetsListViewState.Content(listOf(Pet("Charlie", "")))
            ),
            arrayOf(Result.Failure<List<Pet>>(), PetsListViewState.Error("Something went wrong")),
        )
    }

    @Test
    fun `delivers corresponding state after loading`() {
        // given
        val useCase = mockk<GetPetsUseCase>()
        coEvery { useCase() } returns flow { emit(useCaseResult) }.wrap()
        val subject = PetsListViewModel(useCase)

        // when
        subject.onViewRefreshed()

        // then
        subject.state.getOrAwaitValue() // consume loading
        val result = subject.state.getOrAwaitValue()
        assertEquals(viewState, result)
    }
}

@ExperimentalCoroutinesApi
class LoadingState : CoroutinesTest() {
    @Test
    fun `when performing request, delivers loading state`() {
        // given
        val useCase = mockk<GetPetsUseCase>()
        coEvery { useCase() } returns emptyFlow<Result<List<Pet>>>().wrap()
        val subject = PetsListViewModel(useCase)

        // when
        subject.onViewRefreshed()

        // then
        val result = subject.state.getOrAwaitValue()
        assertEquals(PetsListViewState.Loading, result)
    }
}
