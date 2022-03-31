package online.dingod.dindowsandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import online.dingod.dindowsandroid.demo.DingLogDemoActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_test_log -> {
                startActivity(Intent(this, DingLogDemoActivity::class.java))
            }
        }
    }
}