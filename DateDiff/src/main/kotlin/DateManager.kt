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

        val years = { small: SimplyDate, big: SimplyDate ->
            (big.year - small.year)
                    .conditionalAdd(-1, (small.month > big.month || (small.month == big.month && small.day > big.day)))
        }(dateSmall, dateBig)

        val months = { small: SimplyDate, big: SimplyDate ->
            (big.month - small.month)
                    .let { it.conditionalAdd(12, it < 0) }
                    .run { conditionalAdd(-1, small.day > big.day) }
        }(dateSmall, dateBig)

        val days = { small: SimplyDate, big: SimplyDate ->
            (big.day - small.day)
                    .let { it.conditionalAdd(days_month[small.month], it < 0) }
                    .run { conditionalAdd(-10, months == 0 && GREGORIAN_START_DAYS in countDays(small) until countDays(big)) }
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
                    conditionalAdd(1, (date.month > 1 && isBisestile(date.year)))
                }

        val giorniAnno = (1 until date.year)
                .partition { isBisestile(it) }
                .let { p: Pair<List<Any>, List<Any>> -> Pair(p.first.size, p.second.size) }
                .let { p: Pair<Int, Int> -> p.first * 366 + p.second * 365 }

        return (giorniAnno + giorniMese + giorniGiorno).let { it.conditionalAdd(-10, it > GREGORIAN_START_DAYS) }
    }

    fun isBisestile(year: Int) = year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)

    private fun buildDate(textDate: String) = SimplyDate.Builder(textDate.split("/"))

}

private fun Int.conditionalAdd(value2Add: Int, condition: Boolean): Int = this + if (condition) value2Add else 0

data class SimplyDate(val year: Int, val month: Int, val day: Int) {

    companion object Builder {
        operator fun invoke(split: List<String>): SimplyDate {
            return SimplyDate(split[0].toInt(), split[1].toInt() - 1, split[2].toInt())
        }
    }
}

data class MyDate(val years: Int, val months: Int, val days: Int, val total_days: Int, val negative: Boolean)
