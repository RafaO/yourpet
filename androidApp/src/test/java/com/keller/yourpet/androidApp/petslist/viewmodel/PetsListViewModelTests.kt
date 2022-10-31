package com.keller.yourpet.androidApp.petslist.viewmodel

import com.keller.yourpet.androidApp.utils.CoroutinesTest
import com.keller.yourpet.mobilemain.usecase.BaseFlowableUseCase.Result
import com.keller.yourpet.mobilemain.usecase.GetPetsUseCase
import com.keller.yourpet.shared.CFlow
import com.keller.yourpet.shared.mock.mockPetsList
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.wrap
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

fun mockedSuccess(pets: List<Pet> = emptyList()) = flowOf(Result.Success(pets)).wrap()

@ExperimentalCoroutinesApi
@RunWith(Parameterized::class)
class ViewStateTests(
    private val useCaseResult: CFlow<Result<List<Pet>>>,
    private val viewState: PetsListViewState
) : CoroutinesTest() {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(
                mockedSuccess(),
                PetsListViewState.Content(emptyList())
            ),
            arrayOf(
                mockedSuccess(mockPetsList()),
                PetsListViewState.Content(mockPetsList())
            ),
            arrayOf(
                flowOf(Result.Failure<List<Pet>>()).wrap(),
                PetsListViewState.Error("Something went wrong")
            ),
            arrayOf(
                emptyFlow<Result<List<Pet>>>().wrap(),
                PetsListViewState.Loading
            ),
            arrayOf(
                flowOf(
                    Result.Success(mockPetsList()),
                    Result.Failure()
                ).wrap(),
                PetsListViewState.Content(mockPetsList())
            )
        )
    }

    @Test
    fun `delivers corresponding state for the given flow of results`() {
        // given
        val useCase = mockk<GetPetsUseCase>()
        coEvery { useCase(Filter()) } returns useCaseResult
        val subject = PetsListViewModel(useCase, Filter())

        // when
        subject.onViewRefreshed()

        // then
        assertEquals(viewState, subject.uiState.value)
    }
}
