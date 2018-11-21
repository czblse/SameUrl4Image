package com.example.czb.sameurl4image

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


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


/**
 * 加载头像（网络数据）
 * @param url  图片url
 */
fun ImageView.loaderHeadImgWithHead(url: String) {
    //当url为空时，不请求网络,加载默认图片
    if (url.isEmpty()) {
        Glide.with(context).load(R.drawable.default_head).into(this)
    } else {
        //获取url对应存储在sp中的Last-Modified或Etag
        val key = SharedPreferencesUtils[context, SP_FILE_COMMON, url, ""]
        if (key.isNotEmpty()) {
            //key非空，即本地存在缓存，先加载本地缓存
            glideLoadImg(context, key, url, this)
        } else {
            //key为空，不存在缓存，加载默认图片
            Glide.with(context).load(R.drawable.default_head).into(this)
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
                },{it.printStackTrace()})
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
