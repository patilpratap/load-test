package load_test

import load_test.api.fileDownload
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class App {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(App::class.java)
    }

    fun run() {
        logger.debug("Load test Started")

        fileDownload(100)
    }
}


fun main(args: Array<String>) {
    App().run()
}