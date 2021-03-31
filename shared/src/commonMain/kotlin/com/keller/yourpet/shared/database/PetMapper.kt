package com.keller.yourpet.shared.database

import com.keller.yourpet.shared.model.Pet
import comkelleryourpetshareddata.PetBD

class PetMapper {
    companion object {
        fun from(p: PetBD) = Pet(p.name, p.imageUrl)
    }
}
