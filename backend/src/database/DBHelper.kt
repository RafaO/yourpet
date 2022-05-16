package database

import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

object DbConstants {
    const val DB_NAME_PETS = "pets"
}

class DBHelper(private val collection: CoroutineCollection<Pet>) {

    suspend fun addContent(): Result<Unit> {
        return dbExecute { collection.insertMany(mockPets()) }
    }

    suspend fun getPets(): Result<List<Pet>> {
        return dbExecute { collection.find().toList() }
    }

    private inline fun <T> dbExecute(f: () -> T): Result<T> {
        return try {
            val r = f()
            Result.success(r)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun mockPets() = listOf(
        Pet("Fatality", "https://picsum.photos/id/237/200/150", Gender.Female),
        Pet("Charlie", "https://picsum.photos/id/1025/200/150", Gender.Male)
    )
}
