package database

import kotlinx.coroutines.withTimeout
import org.litote.kmongo.coroutine.CoroutineClient

class DBInitializer(private val client: CoroutineClient) {

    suspend fun checkConnection() {
        withTimeout(1000) {
            val databases = client.listDatabaseNames()
            println("connected to DB, databases: $databases")
        }
    }

    fun getHelper(): DBHelper {
        val database = client.getDatabase(DbConstants.DB_NAME_PETS)
        return DBHelper(collection = database.getCollection())
    }
}
