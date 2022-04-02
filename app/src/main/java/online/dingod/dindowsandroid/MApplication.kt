package online.dingod.dindowsandroid

import android.app.Application
import com.google.gson.Gson
import online.dingod.dinglibrary.log.*

class MApplication : Application() {
    override fun onCreate() {
        super.onCreate()
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