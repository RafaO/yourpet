package com.keller.yourpet.androidApp.petslist.viewmodel

import androidx.compose.material.DrawerValue
import com.keller.yourpet.androidApp.utils.CoroutinesTest
import com.keller.yourpet.androidApp.utils.getOrAwaitValue
import com.keller.yourpet.androidApp.utils.mockPetsList
import com.keller.yourpet.mobilemain.usecase.FlowableUseCase.Result
import com.keller.yourpet.mobilemain.usecase.GetPetsUseCase
import com.keller.yourpet.shared.CFlow
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet
import com.keller.yourpet.shared.wrap
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

fun mockedSuccess() = flowOf(Result.Success(emptyList<Pet>())).wrap()

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
                mockedSuccess(),
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

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(Parameterized::class)
class DrawerStateChangeTests(
    private val newValue: DrawerValue,
    private val useCaseExecution: Int,
) : CoroutinesTest() {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(DrawerValue.Closed, 1),
            arrayOf(DrawerValue.Open, 0),
        )
    }

    @Test
    fun `when drawer closes, executes the use case`() {
        // given
        val useCase = mockk<GetPetsUseCase>()
        val filter = Filter()
        coEvery { useCase(filter) } returns mockedSuccess()
        val subject = PetsListViewModel(useCase, filter)

        // when
        subject.onDrawerStateChanged(newValue)

        // then
        coVerify(exactly = useCaseExecution) { useCase(filter) }
    }

    @Test
    fun `when drawer state changes, returns true`() {
        // given
        val useCase = mockk<GetPetsUseCase>()
        val filter = Filter()
        coEvery { useCase(filter) } returns mockedSuccess()
        val subject = PetsListViewModel(useCase, filter)

        // when
        val result = subject.onDrawerStateChanged(newValue)

        // then
        assert(result)
    }
}
