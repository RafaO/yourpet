package com.keller.yourpet.shared.database

import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet

class PetMapper {
    companion object {
        fun from(id: Long, name: String, imageUrl: String, gender: String, description: String) =
            Pet(id.toString(), name, imageUrl, Gender.valueOf(gender), description)
    }
}
