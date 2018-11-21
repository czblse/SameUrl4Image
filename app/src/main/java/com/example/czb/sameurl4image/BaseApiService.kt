package com.example.czb.sameurl4image

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.HEAD
import retrofit2.http.Header
import retrofit2.http.QueryMap
import retrofit2.http.Url


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

    /** HEAD请求，带上Last-Modified或Etag的请求头 */
    @HEAD
    fun getImg(@Url url: String, @Header(IF_NONE_MATCH) lastModify: String): Observable<Response<Void>>
}
