package com.keller.yourpet.shared.mock

import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet
import kotlin.random.Random

fun mockPet(id: Long = Random.nextLong()) = Pet(
    id.toString(),
    "Fatality",
    "https://picsum.photos/id/237/200/150",
    Gender.Female,
    "Fatality is a great dog"
)

fun mockPetsList() = listOf(
    Pet(
        "1",
        "Fatality",
        "https://picsum.photos/id/237/200/150",
        Gender.Female,
        "Fatality is a great dog"
    ),
    Pet(
        "2",
        "Charlie",
        "https://picsum.photos/id/1025/200/150",
        Gender.Male,
        "Charlie is a bit naughty"
    )
)
