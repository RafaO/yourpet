package com.keller.yourpet.shared.model

import com.keller.yourpet.type.Filter as gFilter
import com.keller.yourpet.type.Gender as gGender

enum class Gender {
    Male {
        override fun toString() = "Male"
    },
    Female {
        override fun toString() = "Female"
    }
}

data class Filter(val genders: MutableSet<Gender> = mutableSetOf()) {
    fun applyTo(pets: List<Pet>) = pets.filter {
        genders.contains(it.gender)
    }

    fun toQueryParam() = gFilter(genders.map {
        when (it) {
            Gender.Male -> gGender.Male
            Gender.Female -> gGender.Female
        }
    })

    fun toMongoQuery(): String {
        val strBuilder = StringBuilder("""{ gender: { ${'$'}in: [""")
        strBuilder.append(genders.joinToString { "\"${it}\"" })
        strBuilder.append("] } }")
        return strBuilder.toString()
    }
}
