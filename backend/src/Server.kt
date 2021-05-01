import com.keller.yourpet.shared.model.Pet
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.serialization.*

fun main() {
    embeddedServer(Netty, 9090) {
        install(ContentNegotiation) {
            json()
        }

        routing {

            get("/pets") {
                val pets = listOf(Pet("Fatality", "https://picsum.photos/id/237/200/150"))
                println(pets)
                call.respond(pets)
            }

        }
    }.start(wait = true)
}
