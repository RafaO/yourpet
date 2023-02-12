package database

import com.keller.yourpet.shared.mock.mockPetsList
import com.keller.yourpet.shared.model.Filter
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.toId

object DbConstants {
    const val DB_NAME_PETS = "pets"
}

class DBHelper(private val collection: CoroutineCollection<MongoPet>) {

    suspend fun addContent() = dbExecute {
        collection.insertMany(mockPetsList().map {
            MongoPet(it.id.toId(), it.name, it.imageUrl, it.gender, it.description)
        })
    }

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
