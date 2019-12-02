import kotlin.math.abs

//fun main() {
//    println(DateManager.diffDate("1582/10/03", "1582/11/03"))
//}

object DateManager {

    const val GREGORIAN_START_DAYS = 577724

    fun diffDate(textDate1: String, textDate2: String): MyDate {

        val date1 = buildDate(textDate1)
        val date2 = buildDate(textDate2)

        val (dateSucc, datePrec) = when {
            date1 > date2 -> Pair(date1, date2)
            else -> Pair(date2, date1)
        }

        val years = { prec: SimplyDate, succ: SimplyDate ->
            (succ.year - prec.year)
                    .conditionalAdd(-1, (prec.month > succ.month || (prec.month == succ.month && prec.day > succ.day)))
        }(datePrec, dateSucc)

        val months = { prec: SimplyDate, succ: SimplyDate ->
            (succ.month - prec.month)
                    .let { it.conditionalAdd(12, it < 0) }
                    .run { conditionalAdd(-1, prec.day > succ.day) }
        }(datePrec, dateSucc)

        val days = { prec: SimplyDate, succ: SimplyDate ->
            (succ.day - prec.day)
                    .let { it.conditionalAdd(SimplyDate.days_month[prec.month], it < 0) }
                    .run { conditionalAdd(-10, prec.year == 1582 && prec.year == succ.year && months == 0 && GREGORIAN_START_DAYS in prec.countDays until succ.countDays) }
        }(datePrec, dateSucc)

        return MyDate(years, months, days, abs(date1.countDays - date2.countDays), date1.countDays > date2.countDays)
    }


    private fun buildDate(textDate: String) = SimplyDate.Builder(textDate.split("/"))

}

private fun Int.conditionalAdd(value2Add: Int, condition: Boolean): Int = this + if (condition) value2Add else 0

data class SimplyDate(val year: Int, val month: Int, val day: Int, val countDays: Int) {

    operator fun compareTo(simplyDate: SimplyDate): Int {
        return countDays.compareTo(simplyDate.countDays)
    }

    constructor(year: Int, month: Int, day: Int) : this(year, month, day, count(year, month, day))

    companion object Builder {

        val days_month = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

        operator fun invoke(split: List<String>): SimplyDate {
            return SimplyDate(split[0].toInt(), split[1].toInt() - 1, split[2].toInt())
        }

        fun count(year: Int, month: Int, day: Int): Int {
            val giorniGiorno = day - 1

            val giorniMese = days_month
                    .stream()
                    .limit(month.toLong())
                    .mapToInt(Int::toInt)
                    .sum()
                    .run { conditionalAdd(1, (month > 1 && isBisestile(year))) }

            val giorniAnno = (1 until year)
                    .partition { isBisestile(it) }
                    .let { p: Pair<List<Any>, List<Any>> -> Pair(p.first.size, p.second.size) }
                    .let { p: Pair<Int, Int> -> p.first * 366 + p.second * 365 }

            return (giorniAnno + giorniMese + giorniGiorno).let { it.conditionalAdd(-10, it > DateManager.GREGORIAN_START_DAYS) }
        }

        fun isBisestile(year: Int) = year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)

    }
}

data class MyDate(val years: Int, val months: Int, val days: Int, val total_days: Int, val negative: Boolean)
