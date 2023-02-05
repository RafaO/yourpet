import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.keller.yourpet.shared.mock.mockPetsList
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet

fun SchemaBuilder.schemaValue(requestHandler: RequestHandler, mockedPets: Boolean = false) {
    query("pets") {
        description = "Retrieve all pets"
        resolver { filter: Filter? ->
            if (mockedPets)
                mockPetsList()
            else
                requestHandler.getPets(filter)
        }
    }

    type<Pet>()
    enum<Gender>()
}
