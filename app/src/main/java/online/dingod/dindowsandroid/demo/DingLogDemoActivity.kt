package online.dingod.dindowsandroid.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import online.dingod.dindowsandroid.R
import online.dingod.dinglibrary.log.DingLog

class DingLogDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ding_log_demo)
        val mBtnLog = findViewById<View>(R.id.btn_log)
        mBtnLog.setOnClickListener {
            printLog()
        }
    }

    private fun printLog() {
        DingLog.a("9900")
    }
}