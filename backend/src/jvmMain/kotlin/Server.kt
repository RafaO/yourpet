import com.apurebase.kgraphql.GraphQL
import database.DBInitializer
import io.ktor.application.*
import io.ktor.server.netty.*
import kotlinx.coroutines.runBlocking
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) = EngineMain.main(args)

/**
 * param testing indicates if it should retrieve mocked data instead of accessing BD
 */
fun Application.module(createContent: Boolean = false) {
    val testing = environment.config.propertyOrNull("testing")?.getString().toBoolean()
    val dbInitializer = DBInitializer(KMongo.createClient().coroutine)
    if (!testing)
        runBlocking {
            dbInitializer.checkConnection()
        }
    val dbHelper = dbInitializer.getHelper()
    if (createContent) {
        runBlocking {
            dbHelper.addContent()
        }
    }
    install(GraphQL) {
        playground = true
        schema {
            schemaValue(RequestHandler(dbHelper), testing)
        }
    }
    println("server started at http://localhost:8080/graphql")
}
