package online.dingod.dindowsandroid.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import online.dingod.dindowsandroid.R
import online.dingod.dinglibrary.log.DingLog
import online.dingod.dinglibrary.log.DingLogManager
import online.dingod.dinglibrary.log.DingViewPrinter
import online.dingod.dinglibrary.util.DingStorageUtil

class DingStorageDemoActivity : AppCompatActivity() {

    var viewPrinter: DingViewPrinter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ding_storage_demo)
        viewPrinter = DingViewPrinter(this)
        viewPrinter!!.viewProvider.showFloatingView()
        DingLogManager.getInstance().addPrinter(viewPrinter)
        DingLog.v(DingStorageUtil.getPath(""))
        DingLog.a(DingStorageUtil.getPath(""))
        DingLog.a("9900")
    }
}