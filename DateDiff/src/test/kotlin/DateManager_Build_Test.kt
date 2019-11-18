import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DateManager_Build_Test {

    @Test
    fun buildYears1() {
        val buildYears = DateManager.buildYears(SimplyDate(2019, 4, 11), SimplyDate(2019, 4, 11))
        assertEquals(0, buildYears)
    }

    @Test
    fun buildYears2() {
        val buildYears = DateManager.buildYears(SimplyDate(2020, 4, 11), SimplyDate(2019, 5, 11))
        assertEquals(0, buildYears)
    }

    @Test
    fun buildYears3() {
        val buildYears = DateManager.buildYears(SimplyDate(2020, 4, 11), SimplyDate(2019, 4, 11))
        assertEquals(1, buildYears)
    }

    @Test
    fun buildYears4() {
        val buildYears = DateManager.buildYears(SimplyDate(2020, 4, 11), SimplyDate(2019, 4, 21))
        assertEquals(0, buildYears)
    }

    @Test
    fun buildYears5() {
        val buildYears = DateManager.buildYears(SimplyDate(2020, 4, 11), SimplyDate(2019, 3, 21))
        assertEquals(1, buildYears)
    }

    @Test
    fun buildMonths1() {
        val buildMonths = DateManager.buildMonths(SimplyDate(2019, 4, 11), SimplyDate(2019, 4, 11))
        assertEquals(0, buildMonths)
    }

    @Test
    fun buildMonths2() {
        val buildMonths = DateManager.buildMonths(SimplyDate(2019, 4, 11), SimplyDate(2019, 3, 21))
        assertEquals(0, buildMonths)
    }

    @Test
    fun buildMonths3() {
        val buildMonths = DateManager.buildMonths(SimplyDate(2019, 4, 21), SimplyDate(2019, 3, 21))
        assertEquals(1, buildMonths)
    }

    @Test
    fun buildMonths4() {
        val buildMonths = DateManager.buildMonths(SimplyDate(2020, 2, 21), SimplyDate(2019, 6, 21))
        assertEquals(8, buildMonths)
    }

    @Test
    fun buildMonths5() {
        val buildMonths = DateManager.buildMonths(SimplyDate(2020, 2, 21), SimplyDate(2019, 6, 23))
        assertEquals(7, buildMonths)
    }

    @Test
    fun buildDays1() {
        val buildDays = DateManager.buildDays(SimplyDate(2019, 4, 11), SimplyDate(2019, 4, 11))
        assertEquals(0, buildDays)
    }

    @Test
    fun buildDays2() {
        val buildDays = DateManager.buildDays(SimplyDate(2019, 4, 21), SimplyDate(2019, 4, 11))
        assertEquals(10, buildDays)
    }

    @Test
    fun buildDays3() {
        val buildDays = DateManager.buildDays(SimplyDate(2019, 5, 11), SimplyDate(2019, 4, 21))
        assertEquals(21, buildDays)
    }

    @Test
    fun buildDays4() {
        val buildDays = DateManager.buildDays(SimplyDate(2019, 2, 1), SimplyDate(2019, 1, 28))
        assertEquals(1, buildDays)
    }

}