package com.keller.yourpet.androidApp.home.viewmodel

import androidx.compose.material.DrawerValue
import com.keller.yourpet.androidApp.home.view.menu.MenuOption
import com.keller.yourpet.androidApp.petslist.viewmodel.mockedSuccess
import com.keller.yourpet.androidApp.utils.CoroutinesTest
import com.keller.yourpet.mobilemain.usecase.GetPetsUseCase
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Gender
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

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
        val subject = HomeViewModel(filter)

        // when
        subject.onGenderSelected(Gender.Male, male)
        subject.onGenderSelected(Gender.Female, female)

        // then
        TestCase.assertEquals(filter, expectedFilter)
    }
}

@RunWith(Parameterized::class)
class NavigationTests(private val selectedOption: MenuOption) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(MenuOption.Settings()),
            arrayOf(MenuOption.Pets(mockk(), false) {}),
        )
    }

    @Test
    fun `when option is selected, it navigates`() {
        // given
        val subject = HomeViewModel(mockk())

        // when
        subject.onOptionSelected(selectedOption)

        // then
        val state = subject.uiState.value
        assertEquals(true, state.shouldNavigate)
        assertEquals(selectedOption, state.optionSelected)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class SimpleTests : CoroutinesTest() {
    @Test
    fun `when drawer closes, emits the ui state with update true`() {
        // given
        val filter = Filter()
        val subject = HomeViewModel(filter)

        // when
        subject.onDrawerStateChanged(DrawerValue.Closed)

        // then
        assertEquals(true, subject.uiState.value.filterUpdated)
    }

    @Test
    fun `when navigated, state reflects it`() {
        // given
        val subject = HomeViewModel(mockk())

        // when
        subject.navigated()

        // then
        assertEquals(false, subject.uiState.value.shouldNavigate)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(Parameterized::class)
class DrawerStateChangeTests(
    private val newValue: DrawerValue,
) : CoroutinesTest() {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(DrawerValue.Closed),
            arrayOf(DrawerValue.Open),
        )
    }

    @Test
    fun `when drawer state changes, returns true`() {
        // given
        val useCase = mockk<GetPetsUseCase>()
        val filter = Filter()
        coEvery { useCase(filter) } returns mockedSuccess()
        val subject = HomeViewModel(filter)

        // when
        val result = subject.onDrawerStateChanged(newValue)

        // then
        assert(result)
    }
}