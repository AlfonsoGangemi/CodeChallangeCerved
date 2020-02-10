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
        while(!msgQueue.isEmpty()){
            println(message = msgQueue.pop().value)
        }
    }
}