import com.apurebase.kgraphql.GraphQL
import database.DBHelper
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.server.netty.EngineMain
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    val createContent = false
    val dbHelper = DBHelper()
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
            schemaValue(dbHelper)
        }
    }
    println("server started")
}
