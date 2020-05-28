package load_test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class App {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(App::class.java)
    }

    fun run() {
        logger.debug("Load test Started")
    }

}

fun main(args: Array<String>) {
    App().run()
}