class PriorityQueue<T> {
    internal val queue : MutableList<Item<T>> = mutableListOf()

    fun push(item:Item<T>) {
        queue.add(computeIndex(queue,item,0,queue.size),item)
    }

    private fun computeIndex(queue: MutableList<Item<T>>, item: Item<T>, min: Int, max: Int): Int {
        if (max-min==0) return min

        val index = (max-min)/2 + min
        return if (queue[index].priority>=item.priority) {
            computeIndex(queue, item, min = index+1, max = max)
        } else {
            computeIndex(queue, item, min = min, max = index)
        }
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



