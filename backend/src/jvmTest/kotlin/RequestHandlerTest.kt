import com.keller.yourpet.shared.model.Filter
import com.keller.yourpet.shared.model.Gender
import com.keller.yourpet.shared.model.Pet
import database.DBHelper
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RequestHandlerTest {

    // TODO move this to a common mock factory module
    private fun mockPetsList() = listOf(Pet("Charlie", "", Gender.Female))
    private val filter = Filter(mutableSetOf(Gender.Female, Gender.Male))

    @Test
    fun `when pets are requested, returns the pets in the db`() = runTest {
        // given
        val dbHelper = mockk<DBHelper>()
        val petsInDB = mockPetsList()
        coEvery { dbHelper.getPets(any()) } returns Result.success(petsInDB)
        val subject = RequestHandler(dbHelper)

        // when
        val result = subject.getPets(filter)

        // then
        assertEquals(petsInDB, result)
    }

    @Test
    fun `when db throws exception, returns empty list`() = runTest {
        // given
        val dbHelper = mockk<DBHelper>()
        coEvery { dbHelper.getPets(any()) } throws RuntimeException()
        val subject = RequestHandler(dbHelper)

        // when
        val result = subject.getPets(filter)

        // then
        assertEquals(emptyList<Pet>(), result)
    }
}
