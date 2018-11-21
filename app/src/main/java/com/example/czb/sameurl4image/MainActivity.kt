package com.example.czb.sameurl4image

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reload_btn.setOnClickListener {
            //加载本地的tomcat图片,url保持不变，更换tomcat上面的图片进行测试
            head_iv.loaderHeadImgWithHead("http://10.10.1.182:8080/a.png")
        }
    }
}
