package com.keller.yourpet.shared.model

sealed class Gender {
    object Male : Gender() {
        override fun toString() = "male"
    }

    object Female : Gender() {
        override fun toString() = "female"
    }
}

data class Filter(val genders: MutableSet<Gender> = mutableSetOf())
