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

        val (dateBig, dateSmall) = when {
            days1 > days2 -> Pair(date1, date2)
            else -> Pair(date2, date1)
        }

        val years = { s: SimplyDate, b: SimplyDate ->
            (b.year - s.year)
                    .conditionalAdd((s.month > b.month || (s.month == b.month && s.day > b.day)), -1)
        }(dateSmall, dateBig)

        val months = { s: SimplyDate, b: SimplyDate ->
            (b.month - s.month)
                    .run { conditionalAdd(this < 0, 12) }
                    .run { conditionalAdd(s.day > b.day, -1) }
        }(dateSmall, dateBig)

        val days = { s: SimplyDate, b: SimplyDate ->
            (b.day - s.day)
                    .run { conditionalAdd(this < 0, days_month[s.month]) }
                    .run { conditionalAdd(months == 0 && GREGORIAN_START_DAYS in countDays(s) until countDays(b), -10) }
        }(dateSmall, dateBig)

        return MyDate(years, months, days, abs(days1 - days2), days1 > days2)
    }

    fun countDays(date: SimplyDate): Int {
        val giorniGiorno = date.day - 1

        val giorniMese = days_month
                .stream()
                .limit(date.month.toLong())
                .mapToInt(Int::toInt)
                .sum()
                .run {
                    conditionalAdd((date.month > 1 && isBisestile(date.year)), 1)
                }

        val giorniAnno = (1 until date.year)
                .partition { isBisestile(it) }
                .let { p: Pair<List<Any>, List<Any>> -> Pair(p.first.size, p.second.size) }
                .let { p: Pair<Int, Int> -> p.first * 366 + p.second * 365 }

        return (giorniAnno + giorniMese + giorniGiorno).run { conditionalAdd(this > GREGORIAN_START_DAYS, -10) }
    }

    fun isBisestile(year: Int) = year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)

    private fun buildDate(textDate: String) = SimplyDate.Builder(textDate.split("/"))

}

private fun Int.conditionalAdd(condition: Boolean, valueAdd: Int): Int = this + if (condition) valueAdd else 0

data class SimplyDate(val year: Int, val month: Int, val day: Int) {
    companion object Builder {
        operator fun invoke(split: List<String>): SimplyDate {
            return SimplyDate(split[0].toInt(), split[1].toInt() - 1, split[2].toInt())
        }
    }
}

data class MyDate(val years: Int, val months: Int, val days: Int, val total_days: Int, val negative: Boolean)
