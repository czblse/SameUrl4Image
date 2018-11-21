package com.kungeek.android.library.network.retrofit

import android.content.Context
import android.text.TextUtils
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.kungeek.android.library.network.BaseApiService
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Retrofit客户端
 * *
 * @version 2.3.0
 * *
 * @date 2017/6/12 16:02
 */

class RetrofitClient
private constructor(context: Context, url: String? = null) {
    /** 基础url */
    private val sBaseUrl = "http://10.0.2.2:8080/"  // 开发_QJM
    /** okhttp */
    private val mOkHttpClient: OkHttpClient
    /** 接口service */
    private val mApiService: BaseApiService
    /** 缓存 */
    private var mCache: Cache? = null
    /** 网络缓存路径 */
    private var mHttpCacheDirectory: File? = null

    fun getApiService(): BaseApiService = mApiService

    companion object {
        /** 默认超时时间 */
        private const val DEFAULT_TIMEOUT = 60

        fun getInstance(context: Context, url: String? = null): RetrofitClient {
            return RetrofitClient(context, url)
        }

    }

    init {
        var urlTemp = url
        if (TextUtils.isEmpty(urlTemp)) {
            urlTemp = sBaseUrl
        }
        if (mHttpCacheDirectory == null) {
            mHttpCacheDirectory = File(context.cacheDir, "net_responses")
        }
        if (mCache == null) {
            mCache = Cache(mHttpCacheDirectory, (10 * 1024 * 1024).toLong())
        }

        mOkHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                // 连接池，同时保持4个连接，每个连接维持15秒
                .connectionPool(ConnectionPool(4, 15, TimeUnit.SECONDS))
                .build()

        val retrofit = Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(urlTemp)
                .build()


        mApiService = retrofit.create(BaseApiService::class.java)
    }
}
