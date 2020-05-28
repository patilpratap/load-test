package load_test.api

import okhttp3.ResponseBody
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.IOUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class DownloadCallback(private val outputFile: File) : Callback<ResponseBody> {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(DownloadCallback::class.java)
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        logger.error("error = ", t)
    }

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        val t = System.currentTimeMillis()
        if (response.isSuccessful) {
            try {
                response.body()?.byteStream().use {
                    FileOutputStream(outputFile).use { os ->
                        IOUtils.copy(it, os)
                    }
                }
//                FileInputStream(outputFile).use {
//                    val hex = DigestUtils.md5Hex(it)
//                    logger.debug("hex = $hex")
//                }
                logger.debug("i = $outputFile, t = ${System.currentTimeMillis() - t}ms")
            } catch (ioe: IOException) {
                logger.error("", ioe)
                logger.error("i = $outputFile, t = ${System.currentTimeMillis() - t}ms")
            }
        } else {
            val code = response.code()
            logger.debug("code = $code")
            logger.error(response.errorBody().toString())
            logger.error("i = $outputFile, t = ${System.currentTimeMillis() - t}ms")
        }
    }
}