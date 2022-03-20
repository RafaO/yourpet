package com.keller.yourpet.androidApp.petslist.view

import androidx.compose.ui.test.junit4.createComposeRule
import com.karumi.shot.ScreenshotTest
import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet
import org.junit.Rule
import org.junit.Test

class PetCardTests : ScreenshotTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun rendersGreetingMessageForTheSpecifiedPerson() {
        composeRule.setContent {
            PetCard(
                pet = Pet(
                    "Charlie",
                    "https://picsum.photos/id/237/200/150",
                    Gender.Female
                ), onPetClicked = {})
        }

        compareScreenshot(composeRule)
    }
}
