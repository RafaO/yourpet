package com.keller.yourpet.androidApp.petslist.view

import androidx.compose.ui.test.junit4.createComposeRule
import com.karumi.shot.ScreenshotTest
import com.keller.yourpet.shared.mock.mockPet
import org.junit.Rule
import org.junit.Test

class PetCardTests : ScreenshotTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun rendersPetCard() {
        composeRule.setContent {
            PetCard(pet = mockPet(), onPetClicked = {})
        }

        compareScreenshot(composeRule)
    }
}
