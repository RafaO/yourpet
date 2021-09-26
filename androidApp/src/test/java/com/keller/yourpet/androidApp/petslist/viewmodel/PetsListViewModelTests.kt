package com.keller.yourpet.androidApp.petslist.viewmodel

import com.keller.yourpet.androidApp.utils.CoroutinesTest
import com.keller.yourpet.androidApp.utils.getOrAwaitValue
import com.keller.yourpet.mobilemain.usecase.FlowableUseCase.Result
import com.keller.yourpet.mobilemain.usecase.GetPetsUseCase
import com.keller.yourpet.mobilemain.usecase.invoke
import com.keller.yourpet.shared.CFlow
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Gender
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
                flowOf(Result.Success(emptyList<Pet>())).wrap(),
                PetsListViewState.Content(emptyList())
            ),
            arrayOf(
                flowOf(Result.Success(listOf(Pet("Charlie", "")))).wrap(),
                PetsListViewState.Content(listOf(Pet("Charlie", "")))
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
                    Result.Success(listOf(Pet("charlie", ""))),
                    Result.Failure()
                ).wrap(),
                PetsListViewState.Content(listOf(Pet("charlie", "")))
            )
        )
    }

    @Test
    fun `delivers corresponding state for the given flow of results`() {
        // given
        val useCase = mockk<GetPetsUseCase>()
        coEvery { useCase() } returns useCaseResult
        val subject = PetsListViewModel(useCase)

        // when
        subject.onViewRefreshed()

        // then
        subject.state.getOrAwaitValue() // consume loading
        val result = subject.state.getOrAwaitValue()
        assertEquals(viewState, result)
    }
}

@RunWith(Parameterized::class)
class FiltersTests(
    private val male: Boolean,
    private val female: Boolean,
    private val expectedFilter: Filter
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(true, true, Filter(mutableSetOf(Gender.Female, Gender.Male))),
            arrayOf(true, false, Filter(mutableSetOf(Gender.Male))),
            arrayOf(false, false, Filter(mutableSetOf())),
            arrayOf(false, true, Filter(mutableSetOf(Gender.Female)))
        )
    }

    @Test
    fun `when gender selected, updates filter accordingly`() {
        // given
        val filter = Filter()
        val subject = PetsListViewModel(mockk(), filter)

        // when
        subject.onGenderSelected(Gender.Male, male)
        subject.onGenderSelected(Gender.Female, female)

        // then
        assertEquals(filter, expectedFilter)
    }
}
