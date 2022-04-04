package online.dingod.dindowsandroid.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import online.dingod.dindowsandroid.R
import online.dingod.dinglibrary.log.DingLog
import online.dingod.dingui.tab.bottom.DingTabBottom
import online.dingod.dingui.tab.bottom.DingTabBottomInfo
import online.dingod.dingui.tab.bottom.DingTabBottomLayout

class DingBottomDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ding_bottom_demo)
        initTabBottom()
    }

    private fun initTabBottom() {
        val tabBottomLayout = findViewById<DingTabBottomLayout>(R.id.ding_tab_layout)
        tabBottomLayout.setTabAlpha(0.85f)
        val bottomInfoList: MutableList<DingTabBottomInfo<*>> = ArrayList()
        val homeInfo = DingTabBottomInfo(
            "首页",
            "font/iconfont.ttf",
            getString(R.string.if_home),
            null,
            "#ff656667",
            "#ffd44949"
        )
        val CollectionInfo = DingTabBottomInfo(
            "收藏",
            "font/iconfont.ttf",
            getString(R.string.if_collection),
            null,
            "#ff656667",
            "#ffd44949"
        )
        val ChatInfo = DingTabBottomInfo(
            "聊天",
            "font/iconfont.ttf",
            getString(R.string.if_chat),
            null,
            "#ff656667",
            "#ffd44949"
        )
        val ProfileInfo = DingTabBottomInfo(
            "我的",
            "font/iconfont.ttf",
            getString(R.string.if_profile),
            null,
            "#ff656667",
            "#ffd44949"
        )
        bottomInfoList.add(homeInfo)
        bottomInfoList.add(CollectionInfo)
        bottomInfoList.add(ChatInfo)
        bottomInfoList.add(ProfileInfo)
        tabBottomLayout.inflateInfo(bottomInfoList)
        tabBottomLayout.addTabSelectedChangeListener{_,_, nextInfo ->
            Toast.makeText(this@DingBottomDemoActivity, nextInfo.name, Toast.LENGTH_SHORT).show()

        }
        tabBottomLayout.defaultSelected(homeInfo)
    }
}