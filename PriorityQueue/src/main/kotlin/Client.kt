import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    val address = "localhost"
    //nc -lk 9999
    val portIn = 9999
    //nc -lk 9898
    val portOut = 9898

    val client = Client(address, portIn, portOut)
    client.run()
}

class Client(address: String, portIn: Int, portOut: Int) {
    private val connectionIn: Socket = Socket(address, portIn)
    private val connectionOut: Socket = Socket(address, portOut)
    private var connected: Boolean = true

    init {
        println("Connected to server at $address on ports: $portIn -> $portOut")
    }

    private val reader: Scanner = Scanner(connectionIn.getInputStream())
    private val writer: OutputStream = connectionOut.getOutputStream()
    private val queue = PriorityQueue<String>()

    fun run() {
        thread { read() }
    }

    private fun write(message: String) {
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
    }

    private fun read() {
        while (connected) {
            val message = reader.nextLine()
            if ("." in message) {
                if (!queue.isEmpty()) write(queue.pop().value)
            } else {
                queue.push(item = Optional
                        .of(message)
                        .map { it.split(",") }
                        .map { if (it.size == 1) it + "0" else it }
                        .map { Item(it[0], it[1].toInt()) }
                        .get())
            }

            println(queue)
        }
    }
}