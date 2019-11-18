import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

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