import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DateManager_DiffDate_Test {

    @Test
    fun diffDate1() {
        val (years, months, days, total_days, negative) = DateManager.diffDate("2019/05/11", "2019/06/11")
        assertEquals(0, years)
        assertEquals(1, months)
        assertEquals(0, days)
        assertEquals(31, total_days)
        assertEquals(false, negative)
    }

    @Test
    fun diffDate2() {
        val (years, months, days, total_days, negative) = DateManager.diffDate("2020/02/29", "2019/06/11")
        assertEquals(0, years)
        assertEquals(8, months)
        assertEquals(18, days)
        assertEquals(263, total_days)
        assertEquals(true, negative)
    }

    @Test
    fun diffDate3() {
        val diffDate = DateManager.diffDate("2020/03/29", "2017/07/03")
        assertEquals(1000,diffDate.total_days)

    }

    @Test
    fun diffDate4() {
        val diffDate = DateManager.diffDate("2020/02/29", "2017/06/04")
        assertEquals(1000,diffDate.total_days)
    }
}

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

internal class DateManager_IsBisestile_Test {

    @Test
    fun isBisestile400() {
        val bisestile = DateManager.isBisestile(2000)
        assertEquals(true,bisestile)
    }

    @Test
    fun isBisestile100() {
        val bisestile = DateManager.isBisestile(1900)
        assertEquals(false,bisestile)
    }

    @Test
    fun isBisestile4() {
        val bisestile = DateManager.isBisestile(1920)
        assertEquals(true,bisestile)
    }

    @Test
    fun isBisestile123() {
        val bisestile = DateManager.isBisestile(2023)
        assertEquals(false,bisestile)
    }
}