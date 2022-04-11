package online.dingod.dindowsandroid.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import online.dingod.dindowsandroid.R
import online.dingod.dingui.refresh.DingRefreshLayout

class DingScrollDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ding_scroll_demo)
        val refreshLayout = findViewById<DingRefreshLayout>(R.id.refresh_layout)

    }
}