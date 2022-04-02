package online.dingod.dindowsandroid

import android.app.Application
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import online.dingod.dinglibrary.log.DingConsolePrinter
import online.dingod.dinglibrary.log.DingLogConfig
import online.dingod.dinglibrary.log.DingLogConfig.JsonParser
import online.dingod.dinglibrary.log.DingLogManager


class MApplication : Application() {

    var PATH: String? = null

    override fun onCreate() {
        super.onCreate()
        PATH = ContextCompat.getExternalFilesDirs(this, null)[0].absolutePath
        DingLogManager.init(
            object : DingLogConfig() {
                override fun getGlobalTag(): String {
                    return "MApplication"
                }

                override fun enable(): Boolean {
                    return true
                }

                override fun injectJsonParser(): JsonParser {
                    return JsonParser { src -> Gson().toJson(src) }
                }
            },
            DingConsolePrinter()
        )
    }
}