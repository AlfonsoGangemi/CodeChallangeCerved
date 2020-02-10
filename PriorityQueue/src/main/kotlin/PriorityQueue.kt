class PriorityQueue<T> {
    internal val queue : MutableList<Item<T>> = mutableListOf()

    fun push(item:Item<T>) {
        val iter = queue.listIterator()
        while (iter.hasNext()) {
            val next = iter.next()
            if(next.priority<item.priority) {
                iter.previous()
                break
            }
        }
        iter.add(item)
    }

    fun pop():Item<T>{
        if(queue.size==0) {
            throw EmptyQueueException()
        }
        return queue.removeAt(0)
    }

    fun isEmpty():Boolean {
        return queue.size==0
    }

    override fun toString(): String {
        return "PriorityQueue($queue)"
    }

}

class EmptyQueueException : Throwable()

data class Item<T>(val value:T,val priority:Int = 0)



