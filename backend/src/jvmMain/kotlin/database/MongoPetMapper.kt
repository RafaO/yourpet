package database

import com.keller.yourpet.shared.model.Pet
import org.litote.kmongo.toId

class MongoPetMapper {
    companion object {
        fun toPet(mongoPet: MongoPet) =
            Pet(
                mongoPet._id.toString(),
                mongoPet.name,
                mongoPet.imageUrl,
                mongoPet.gender,
                mongoPet.description
            )

        fun toMongoPet(pet: Pet) =
            MongoPet(pet.id.toId(), pet.name, pet.imageUrl, pet.gender, pet.description)
    }
}
