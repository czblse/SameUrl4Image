package com.example.czb.sameurl4image

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.kungeek.android.library.network.retrofit.RetrofitClient
import com.kungeek.android.library.util.SP_FILE_COMMON
import com.kungeek.android.library.util.SharedPreferencesUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response


/**
 * 图片相关工具类
 *
 * @date 2018/2/27 18:27
 * @version v4.0.0
 */
const val ETAG = "ETag"
const val IF_NONE_MATCH = "If-None-Match"
const val LAST_MODIFIED = "Last-Modified"
const val IF_MODIFIED_SINCE = "If-Modified-Since"

///**
// * 加载头像（网络数据）
// * @param url  图片url
// */
//fun ImageView.loaderHeadImg(url: String) {
//    //当url为空时，不请求网络,加载默认图片
//    if (url.isEmpty()) {
//        Glide.with(context).load(R.drawable.default).into(this)
//    } else {
//        //判断用户是不是第一次请求该图片
//        val lastModified = SharedPreferencesUtils[context, SP_FILE_COMMON, url, ""]
//        //字段不为null和“”,则不是第一次请求该图片
//        if (lastModified.isNotEmpty()) {
//            Log.d("IMG", "lastModified不为空")
//            //先访问图片的信息，只拿到响应头（Head请求）,如果Last-Modified跟本地缓存图片不一致则向服务器请求最新图片
//            loadNewImg(url, lastModified)
//        } else {
//            Log.d("IMG", "lastModified为空，第一次请求该图片")
//            //第一次请求该图片时，没有缓存，先加载本地占位图
//            Glide.with(context).load(R.drawable.default).into(this)
//            //先访问图片的信息，只拿到响应头（Head请求）,如果Last-Modified跟本地缓存图片不一致则向服务器请求最新图片
//            loadNewImg(url, lastModified)
//        }
//    }
//}
//
//
//private fun ImageView.loadNewImg(url: String, lastModified: String) {
//    RetrofitClient.getInstance(context).getApiService().executeGetWithHeaders(url, mapOf())
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ t: Response<Void> ->
//                //访问图片成功时
//                if (t.code() == 200) {
//                    val dateModified = t.headers().get(LAST_MODIFIED)
//                    Log.d("IMG", "返回的lastModified：" + dateModified)
//                    //将Last-Modified字段存到SP里面
//                    SharedPreferencesUtils.save(context, SP_FILE_COMMON, url, dateModified!!)
//                    //请求最新的图片
//                    glideLoadImg(context, lastModified, url, this)
//                }
//            }, { t: Throwable -> t.printStackTrace() })
//}



/**
 * 加载头像（网络数据）
 * @param url  图片url
 */
fun ImageView.loaderHeadImgWithHead(url: String) {
    //当url为空时，不请求网络,加载默认图片
    if (url.isEmpty()) {
        Glide.with(context).load(R.drawable.default).into(this)
    } else {
        //获取url对应存储在sp中的Last-Modified或Etag
        val key = SharedPreferencesUtils[context, SP_FILE_COMMON, url, ""]
        if (key.isNotEmpty()) {
            //key非空，即本地存在缓存，先加载本地缓存
            glideLoadImg(context, key, url, this)
        } else {
            //key为空，不存在缓存，加载默认图片
            Glide.with(context).load(R.drawable.default).into(this)
        }
        RetrofitClient.getInstance(context).getApiService().getImg(url, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //获取响应头中的Last-Modified或Etag
                    val head = it.headers().get(ETAG)
                    if (it.code() == 304) {
                        //304的时候返回的lastModified或者Etag为null，此时使用上次存储的key来加载图片
                        glideLoadImg(context, key, url, this)
                    } else if (it.code() == 200) {
                        //保存最新的lastModified或者Etag
                        SharedPreferencesUtils.save(context, SP_FILE_COMMON, url, head!!)
                        glideLoadImg(context, head, url, this)
                    }
                })
    }


}


/**
 * 使用Glide加载图片
 * @param context  上下文
 * @param key  Last-Modified或Etag
 * @param url  图片url
 * @param imageView  图片控件
 */
private fun glideLoadImg(context: Context, key: String, url: String, imageView: ImageView) {
    Glide.with(context)
            .setDefaultRequestOptions(RequestOptions.circleCropTransform()
                    //图片签名信息，相同url下如果需要刷新图片，signature不同则会加载网络端的图片资源
                    .signature(ObjectKey(key)).placeholder(imageView.drawable))
            .load(url)
            .into(imageView)
}
