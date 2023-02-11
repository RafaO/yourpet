package database

import com.keller.yourpet.shared.model.Pet

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
    }
}
