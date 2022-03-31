package online.dingod.dindowsandroid

import android.app.Application
import online.dingod.dinglibrary.log.DingLogConfig
import online.dingod.dinglibrary.log.DingLogManager

class MApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DingLogManager.init(object :DingLogConfig(){
            override fun getGlobalTag(): String {
                return "MApplication"
            }

            override fun enable(): Boolean {
                return true
            }
        })
    }
}