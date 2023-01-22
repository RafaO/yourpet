package com.keller.yourpet.shared.database

import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet

class PetMapper {
    companion object {
        // id kept despite unused so the method can be passed as reference
        fun from(id: Long, name: String, imageUrl: String, gender: String) =
            Pet(name, imageUrl, Gender.valueOf(gender))
    }
}
