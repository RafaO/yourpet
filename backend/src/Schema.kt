import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet
import database.DBHelper
import kotlinx.coroutines.runBlocking

fun SchemaBuilder.schemaValue(dbHelper: DBHelper) {
    query("pets") {
        description = "Retrieve all pets"
        resolver { filter: Filter? ->
            try {
                runBlocking {
                    val pets = dbHelper.getPets().getOrThrow()
                    filter?.applyTo(pets) ?: pets
                }
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    type<Pet>()
    enum<Gender>()
}
