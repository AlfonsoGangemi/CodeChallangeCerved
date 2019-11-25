import kotlin.math.abs

//fun main() {
//    println(DateManager.diffDate("1582/10/03", "1582/11/03"))
//}

object DateManager {

    private val days_month = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    private const val GREGORIAN_START_DAYS = 577724

    fun diffDate(textDate1: String, textDate2: String): MyDate {

        val date1 = buildDate(textDate1)
        val date2 = buildDate(textDate2)

        val days1 = countDays(date1)
        val days2 = countDays(date2)

        val years = when {
            days1 > days2 -> buildYears(date1, date2)
            else -> buildYears(date2, date1)
        }
        val months = when {
            days1 > days2 -> buildMonths(date1, date2)
            else -> buildMonths(date2, date1)
        }
        val days = when {
            days1 > days2 -> buildDays(date1, date2)
            else -> buildDays(date2, date1)
        }

        return MyDate(years, months, days, abs(days1 - days2), days1 > days2)
    }

    fun buildYears(dateBig: SimplyDate, dateSmall: SimplyDate): Int {
        var years = dateBig.year - dateSmall.year
        if (dateSmall.month > dateBig.month
                || (dateSmall.month == dateBig.month && dateSmall.day > dateBig.day)) years--
        return years
    }

    fun buildMonths(dateBig: SimplyDate, dateSmall: SimplyDate): Int {
        var months = dateBig.month - dateSmall.month
        months.takeIf { x -> x < 0 }?.apply { months += 12 }
//        if (months < 0) months += 12
        if (dateSmall.day > dateBig.day) months--
        return months
    }

    fun buildDays(dateBig: SimplyDate, dateSmall: SimplyDate): Int {
        var days = dateBig.day - dateSmall.day
        days.takeIf { x -> x < 0 }?.apply { days += days_month[dateSmall.month] }
//        if (days < 0) days += days_month[dateSmall.month]
        if (buildMonths(dateBig, dateSmall) == 0 && GREGORIAN_START_DAYS in countDays(dateSmall) until countDays(dateBig)) days -= 10
        return days
    }

    fun countDays(date: SimplyDate): Int {
        var count = date.day - 1

        count += days_month.stream().limit(date.month.toLong()).mapToInt(Int::toInt).sum()
        if (date.month > 1 && isBisestile(date.year)) count++

        count += (1 until date.year)
                .partition { isBisestile(it) }
                .let { p: Pair<List<Any>, List<Any>> -> Pair(p.first.size, p.second.size) }
                .let { p: Pair<Int, Int> -> p.first * 366 + p.second * 365 }

        if (count > GREGORIAN_START_DAYS) count -= 10

        return count
    }

    fun isBisestile(year: Int) = year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)

    private fun buildDate(textDate: String) = SimplyDate.Builder(textDate.split("/"))

}

data class SimplyDate(val year: Int, val month: Int, val day: Int) {
    companion object Builder {
        operator fun invoke(split: List<String>): SimplyDate {
            return SimplyDate(split[0].toInt(), split[1].toInt() - 1, split[2].toInt())
        }
    }
}

data class MyDate(val years: Int, val months: Int, val days: Int, val total_days: Int, val negative: Boolean)
