import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PriorityQueueTest {

    private val msgQueue = PriorityQueue<String>()

    @Test
    fun pop() {
        msgQueue.push(Item("Ciao"))
        msgQueue.push(Item("sistema",5))
        msgQueue.push(Item("Importante",10))
        msgQueue.push(Item("Saluti"))
        msgQueue.push(Item("Importante2",10))
        msgQueue.push(Item("Arrivederci"))
        msgQueue.push(Item("Importante3",10))
        while(!msgQueue.isEmpty()){
            println(message = msgQueue.pop().value)
        }
    }

    @Test
    fun pushInEmpty() {
        msgQueue.push(Item("Test",5))
        while(!msgQueue.isEmpty()){
            println(message = msgQueue.pop().value)
        }
    }

    @Test
    fun pushBeforeFirst() {
        msgQueue.push(Item("Test",5))
        msgQueue.push(Item("NewItem",1))
        assertEquals("Test",msgQueue.pop().value)
        while(!msgQueue.isEmpty()){
            println(message = msgQueue.pop().value)
        }
    }

    @Test
    fun pushBeforeFirstSamePriority() {
        msgQueue.push(Item("Test",5))
        msgQueue.push(Item("NewItem",5))
        assertEquals("Test",msgQueue.pop().value)
        while(!msgQueue.isEmpty()){
            println(message = msgQueue.pop().value)
        }
    }

    @Test
    fun pushAfterFirst() {
        msgQueue.push(Item("Test",5))
        msgQueue.push(Item("NewItem",10))
        assertEquals("NewItem",msgQueue.pop().value)
        while(!msgQueue.isEmpty()){
            println(message = msgQueue.pop().value)
        }
    }

    @Test
    fun footballRank() {
        msgQueue.push(Item("Inter",2))
        msgQueue.push(Item("Roma",4))
        msgQueue.push(Item("Napoli",10))
        msgQueue.push(Item("Atalanta",5))
        msgQueue.push(Item("Cagliari",6))
        msgQueue.push(Item("Juventus",1))
        msgQueue.push(Item("Milan",8))
        msgQueue.push(Item("Lazio",3))
        msgQueue.push(Item("Parma",7))
        while(!msgQueue.isEmpty()){
            println(message = msgQueue.pop().value)
        }
    }
}