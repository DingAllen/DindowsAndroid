package online.dingod.dindowsandroid.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import online.dingod.dindowsandroid.R
import online.dingod.dinglibrary.log.*

class DingLogDemoActivity : AppCompatActivity() {

    var viewPrinter: DingViewPrinter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ding_log_demo)
        viewPrinter = DingViewPrinter(this)
        val mBtnLog = findViewById<View>(R.id.btn_log)
        mBtnLog.setOnClickListener {
            printLog()
        }
        viewPrinter!!.viewProvider.showFloatingView()
    }

    private fun printLog() {
        DingLogManager.getInstance().addPrinter(viewPrinter)
        DingLog.log(object : DingLogConfig() {
            override fun includeThread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }
        }, DingLogType.E, "-----", "0216")
        DingLog.a("9900")
    }
}