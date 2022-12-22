package com.keller.yourpet.androidApp.main.viewmodel

import com.keller.yourpet.androidApp.utils.CoroutinesTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@ExperimentalCoroutinesApi
@RunWith(Parameterized::class)
class MainViewModelTests(private val selectedColor: ThemeColor) : CoroutinesTest() {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(ThemeColor.DARK),
            arrayOf(ThemeColor.LIGHT),
            arrayOf(ThemeColor.SYSTEM),
        )
    }

    @Test
    fun `when theme color selected, emits the corresponding uiState`() = runTest {
        // given
        val subject = MainViewModel()
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            subject.uiState.collect {}
        }

        // when
        subject.onThemeColorSelected(selectedColor)

        // then
        assertEquals(selectedColor, subject.uiState.value.themeColor)

        // tear down
        collectJob.cancel()
    }
}
