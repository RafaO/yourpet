package database

import com.keller.yourpet.shared.model.Gender
import org.litote.kmongo.Id

data class MongoPet(
    val _id: Id<MongoPet>,
    val name: String,
    val imageUrl: String,
    val gender: Gender,
    val description: String
)
