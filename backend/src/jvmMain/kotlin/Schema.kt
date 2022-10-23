import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet

fun SchemaBuilder.schemaValue(requestHandler: RequestHandler) {
    query("pets") {
        description = "Retrieve all pets"
        resolver { filter: Filter? ->
            requestHandler.getPets(filter)
        }
    }

    type<Pet>()
    enum<Gender>()
}
