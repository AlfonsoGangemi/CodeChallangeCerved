import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DateManager_CountDays_Test {

    @Test
    fun countDaysStart() {
        val days = DateManager.countDays(SimplyDate(1900, 0, 1))
        assertEquals(0, days)
    }

    @Test
    fun countDaysOneYear() {
        val days = DateManager.countDays(SimplyDate(1901, 0, 1))
        assertEquals(365, days)
    }

    @Test
    fun countDaysTwoYear() {
        val days = DateManager.countDays(SimplyDate(1902, 0, 1))
        assertEquals(365+365, days)
    }

    @Test
    fun countDaysFourYear() {
        val days = DateManager.countDays(SimplyDate(1905, 0, 1))
        assertEquals(365*5+1, days)
    }

    @Test
    fun countDaysOneMonth() {
        val days = DateManager.countDays(SimplyDate(1900, 1, 1))
        assertEquals(31, days)
    }

    @Test
    fun countDaysTwoMonth() {
        val days = DateManager.countDays(SimplyDate(1900, 2, 1))
        assertEquals(31+28, days)
    }

    @Test
    fun countDaysFourMonth() {
        val days = DateManager.countDays(SimplyDate(1900, 4, 1))
        assertEquals(31+28+31+30, days)
    }

    @Test
    fun countDaysFourYearThreeMonthTwoDay() {
        val days = DateManager.countDays(SimplyDate(1905, 3, 3))
        assertEquals(365*5+1+31+28+31+2, days)
    }

    @Test
    fun countDaysCustom() {
        val days = DateManager.countDays(SimplyDate(2019, 11, 31))
        print(days)
    }

}