import com.apurebase.kgraphql.GraphQL
import database.DBHelper
import database.DbConstants
import io.ktor.application.*
import io.ktor.server.netty.*
import kotlinx.coroutines.runBlocking
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) = EngineMain.main(args)

/**
 * param testing indicates if it should retrieve mocked data instead of accessing BD
 */
fun Application.module(testing: Boolean = false, createContent: Boolean = false) {
    val client = KMongo.createClient().coroutine
    val database = client.getDatabase(DbConstants.DB_NAME_PETS)
    val dbHelper = DBHelper(collection = database.getCollection())
    if (createContent) {
        runBlocking {
            val result = dbHelper.addContent()
            if (result.isFailure) {
                println("content couldn't be created")
            } else {
                println("content added")
            }
        }
    }
    install(GraphQL) {
        playground = true
        schema {
            schemaValue(RequestHandler(dbHelper), testing)
        }
    }
    println("server started")
}
