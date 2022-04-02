package online.dingod.dindowsandroid.demo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import online.dingod.dindowsandroid.R
import online.dingod.dinglibrary.log.DingLog
import online.dingod.dinglibrary.log.DingLogManager
import online.dingod.dinglibrary.log.DingViewPrinter
import online.dingod.dinglibrary.util.DingStorageUtil
import java.io.File
import java.io.FileOutputStream


class DingStorageDemoActivity : AppCompatActivity() {

    var viewPrinter: DingViewPrinter? = null
    var logPath : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ding_storage_demo)
        viewPrinter = DingViewPrinter(this)
        viewPrinter!!.viewProvider.showFloatingView()
        DingLogManager.getInstance().addPrinter(viewPrinter)
        logPath = DingStorageUtil.getLogPath(
            ContextCompat.getExternalFilesDirs(
                this,
                null
            )[0].absolutePath
        )
        // logPath = DingStorageUtil.getLogPath()
        DingLog.v("我已出舱，感觉良好")
    }

    public fun save(view: View) {

        DingLog.v(logPath)
        val f = File(logPath)
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs()
        }
        if (!f.exists()) {
            f.createNewFile()
        }
        val fileOutputStream = FileOutputStream(logPath, true)
        DingLog.v("lets go warriors")
        fileOutputStream.write("let's go warriors \n".toByteArray())
    }
}