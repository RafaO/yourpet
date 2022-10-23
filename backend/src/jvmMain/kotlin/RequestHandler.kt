import com.keller.yourpet.shared.model.Filter
import database.DBHelper
import kotlinx.coroutines.runBlocking

class RequestHandler(private val dbHelper: DBHelper) {
    fun getPets(filter: Filter?) = try {
        runBlocking {
            dbHelper.getPets(filter).getOrThrow()
        }
    } catch (e: Exception) {
        emptyList()
    }
}
