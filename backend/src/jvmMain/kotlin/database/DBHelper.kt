package database

import com.keller.yourpet.shared.mock.mockPetsList
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Pet
import org.litote.kmongo.coroutine.CoroutineCollection

object DbConstants {
    const val DB_NAME_PETS = "pets"
}

class DBHelper(private val collection: CoroutineCollection<Pet>) {

    suspend fun addContent() = dbExecute { collection.insertMany(mockPetsList()) }

    suspend fun getPets(filter: Filter?) = if (filter != null)
        dbExecute { collection.find(filter.toMongoQuery()).toList() }
    else
        dbExecute { collection.find().toList() }

    private inline fun <T> dbExecute(f: () -> T) = try {
        val r = f()
        println("success, ${r.toString()}")
        Result.success(r)
    } catch (e: Exception) {
        println("error: $e")
        Result.failure(e)
    }
}
