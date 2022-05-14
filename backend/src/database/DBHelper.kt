package database

import com.keller.yourpet.shared.model.Pet
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import pets

object DbConstants {
    const val DB_NAME_PETS = "pets"
}

class DBHelper {

    private val collection: CoroutineCollection<Pet>

    init {
        val client = KMongo.createClient().coroutine
        val database = client.getDatabase(DbConstants.DB_NAME_PETS)
        collection = database.getCollection()
    }

    suspend fun addContent(): Result<Unit> {
        return dbExecute { collection.insertMany(pets()) }
    }

    private inline fun <T> dbExecute(f: () -> T): Result<T> {
        return try {
            val r = f()
            Result.success(r)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
