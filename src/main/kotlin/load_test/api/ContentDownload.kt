package load_test.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ContentDownload {

    // http://192.168.1.106:9001/content/file/stream?fileName=Equine%20Walk%20300fps&ext=avi
    @GET("content/file/stream")
    fun downloadContentFile(
        @Query("fileName")
        fileName: String,
        @Query("ext")
        ext: String
    ): Call<ResponseBody>


    object RetrofitHelper {

        private val contentDownloadService: ContentDownload = Retrofit.Builder()
            .baseUrl("http://localhost:9001/")
            .build()
            .create(ContentDownload::class.java)

        fun contentDownloadService(): ContentDownload = contentDownloadService

    }

}