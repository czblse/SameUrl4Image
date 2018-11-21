package com.example.czb.sameurl4image

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.kungeek.android.library.util.SP_FILE_COMMON
import com.kungeek.android.library.util.SharedPreferencesUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.InputStream

//@GlideModule
class MyOkHttpGlideModule : AppGlideModule() {
    private val client = OkHttpClient.Builder()
            .addInterceptor(HeadInterceptor())
            .addNetworkInterceptor(HeadInterceptor())
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    override fun registerComponents(context: Context?, glide: Glide?, registry: Registry?) {
        registry?.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(client))
    }

    class HeadInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
            val build = request.build()
            val url = build.url()
            //这里添加我们需要的请求头Last-Modified
            val lastModify = SharedPreferencesUtils[BaseApp.getInstance(), SP_FILE_COMMON, url.toString(), ""]
            request.addHeader(LAST_MODIFIED, lastModify)
//            Log.e("okhttp","添加的请求头为  "+lastModify)
            val response = chain.proceed(build)
            //请求成功以后保存响应头Last-Modified
            SharedPreferencesUtils.save(BaseApp.getInstance(), SP_FILE_COMMON,url.toString(),response.header(LAST_MODIFIED)?:"")
//            Log.e("okhttp","返回的响应头头为  "+response.header(LAST_MODIFIED)?:"")
            return response
        }
    }
}