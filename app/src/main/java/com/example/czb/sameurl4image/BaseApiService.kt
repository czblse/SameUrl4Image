package com.kungeek.android.library.network

import com.example.czb.sameurl4image.IF_MODIFIED_SINCE
import com.example.czb.sameurl4image.IF_NONE_MATCH
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


/**
 * 基础api方法，包括POST、GET、UPLOAD、DOWNLOAD等

 * @version 2.2.0
 * *
 * @date 2017/5/16 16:49
 */

interface BaseApiService {
    /** HEAD请求，只会返回响应头，没有返回响应体，节省流量 */
    @HEAD
    fun executeGetWithHeaders(@Url url: String, @QueryMap maps: Map<String, String>): Observable<Response<Void>>

    @HEAD
    fun getImg(@Url url: String, @Header(IF_NONE_MATCH) lastModify: String): Observable<Response<Void>>
}
