package online.dingod.dindowsandroid.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import online.dingod.dindowsandroid.R
import online.dingod.dingui.tab.bottom.DingTabBottom
import online.dingod.dingui.tab.bottom.DingTabBottomInfo

class DingBottomDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ding_bottom_demo)
        val tabBottom = findViewById<DingTabBottom>(R.id.tab_bottom)
        val homeInfo = DingTabBottomInfo(
            "首页",
            "font/iconfont.ttf",
            getString(R.string.if_home),
            null,
            "#ff656667",
            "ffd44949"
        )
        tabBottom.setDingTabInfo(homeInfo)
    }
}