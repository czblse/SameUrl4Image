package com.example.czb.sameurl4image

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reload_btn.setOnClickListener {
            head_iv.loaderHeadImgWithHead("http://10.0.2.2:8080/a.png")
        }
    }
}
