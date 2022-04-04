package online.dingod.dindowsandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import online.dingod.dindowsandroid.demo.DingBottomDemoActivity
import online.dingod.dindowsandroid.demo.DingLogDemoActivity
import online.dingod.dindowsandroid.demo.DingStorageDemoActivity
import online.dingod.dindowsandroid.testproject.TestActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_test -> {
                startActivity(Intent(this, TestActivity::class.java))
            }
        }
    }
}