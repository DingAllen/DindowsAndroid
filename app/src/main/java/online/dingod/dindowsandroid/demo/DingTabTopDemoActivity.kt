package online.dingod.dindowsandroid.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import online.dingod.dindowsandroid.R
import online.dingod.dingui.tab.top.DingTabTop
import online.dingod.dingui.tab.top.DingTabTopInfo
import online.dingod.dingui.tab.top.DingTabTopLayout

class DingTabTopDemoActivity : AppCompatActivity() {

    var tabsStr = arrayOf(
        "热门",
        "服装",
        "数码",
        "鞋子",
        "零食",
        "家电",
        "汽车",
        "百货",
        "家具",
        "装修",
        "运动"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ding_tab_top_demo)
        initTabTop()
    }

    private fun initTabTop() {

        val tabTopLayout = findViewById<DingTabTopLayout>(R.id.tab_top_layout)
        val infoList: MutableList<DingTabTopInfo<*>> = ArrayList()

        val defaultColor = resources.getColor(R.color.tabTopDefaultColor)
        val tintColor = resources.getColor(R.color.tabTopTintColor)

        for (s in tabsStr) {
            val info = DingTabTopInfo<Int>(s, defaultColor, tintColor)
            infoList.add(info)
        }
        tabTopLayout.inflateInfo(infoList)
        tabTopLayout.addTabSelectedChangeListener { _, _, nextInfo ->
            Toast.makeText(this, nextInfo.name, Toast.LENGTH_SHORT).show()
        }
        tabTopLayout.defaultSelected(infoList[0])
    }
}