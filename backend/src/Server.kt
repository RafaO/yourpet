import com.apurebase.kgraphql.GraphQL
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(GraphQL) {
        playground = true
        schema {
            schemaValue()
        }
    }
}
