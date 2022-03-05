package com.keller.yourpet.shared.model

enum class Gender {
    Male {
        override fun toString() = "male"
    },
    Female {
        override fun toString() = "female"
    }
}

data class Filter(val genders: MutableSet<Gender> = mutableSetOf())
