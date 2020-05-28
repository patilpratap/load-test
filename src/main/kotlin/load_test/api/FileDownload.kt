package load_test.api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Paths

const val dir = "/home/pratap.patil/Desktop/test/load-test/"

private val logger: Logger = LoggerFactory.getLogger("fileDownload")

fun fileDownload(n: Int) {

    val contentDownload: ContentDownload = ContentDownload.RetrofitHelper.contentDownloadService()

    for (i in 1..n) {
        contentDownload.downloadContentFile("Equine Walk 300fps", "avi")
            .enqueue(DownloadCallback(Paths.get(dir, "$i.avi").toFile()))
    }

}
