import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.keller.yourpet.shared.model.Pet

fun SchemaBuilder.schemaValue() {
    query("pets") {
        description = "Retrieve all pets"
        resolver { ->
            try {
                listOf(Pet("Fatality", "https://picsum.photos/id/237/200/150"))
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    type<Pet>()
}
