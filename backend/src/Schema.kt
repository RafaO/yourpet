import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet

fun pets() = listOf(Pet("Fatality", "https://picsum.photos/id/237/200/150", Gender.Female))

fun SchemaBuilder.schemaValue() {
    query("pets") {
        description = "Retrieve all pets"
        resolver { filter: Filter? ->
            try {
                filter?.applyTo(pets()) ?: pets()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    type<Pet>()
    enum<Gender>()
}
