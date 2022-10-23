import com.apurebase.kgraphql.GraphQL
import database.DBHelper
import database.DbConstants
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.server.netty.EngineMain
import kotlinx.coroutines.runBlocking
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    val createContent = false
    val client = KMongo.createClient().coroutine
    val database = client.getDatabase(DbConstants.DB_NAME_PETS)
    val dbHelper = DBHelper(collection = database.getCollection())
    if (createContent) {
        runBlocking {
            val result = dbHelper.addContent()
            if (result.isFailure) {
                println("content couldn't be created")
            }
        }
    }
    install(GraphQL) {
        playground = true
        schema {
            schemaValue(RequestHandler(dbHelper))
        }
    }
    println("server started")
}
