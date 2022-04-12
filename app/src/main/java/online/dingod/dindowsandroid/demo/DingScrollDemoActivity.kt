package online.dingod.dindowsandroid.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import online.dingod.dindowsandroid.R
import online.dingod.dingui.refresh.DingOverView
import online.dingod.dingui.refresh.DingRefresh
import online.dingod.dingui.refresh.DingRefreshLayout
import online.dingod.dingui.refresh.DingTextOverView

class DingScrollDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ding_scroll_demo)
        val refreshLayout = findViewById<DingRefreshLayout>(R.id.refresh_layout)
        val textOverView = DingTextOverView(this)
        refreshLayout.setRefreshOverView(textOverView)
        refreshLayout.setRefreshListener(object :DingRefresh.DingRefreshListener{
            override fun onRefresh() {
                Handler().postDelayed({ refreshLayout.refreshFinished()}, 500)
            }

            override fun enableRefresh(): Boolean {
                return true
            }
        })
    }
}