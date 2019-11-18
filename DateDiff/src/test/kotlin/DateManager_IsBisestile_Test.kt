import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

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