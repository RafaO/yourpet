package com.keller.yourpet.androidApp.main.viewmodel

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class MainViewModelTests(private val selectedColor: ThemeColor) {
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
    fun `when theme color selected, emits the corresponding uiState`() {
        // given
        val subject = MainViewModel()

        // when
        subject.onThemeColorSelected(selectedColor)

        // then
        assertEquals(selectedColor, subject.uiState.value.themeColor)
    }
}
