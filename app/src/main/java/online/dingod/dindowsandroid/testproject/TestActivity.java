package online.dingod.dindowsandroid.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import online.dingod.common.ui.component.DingBaseActivity;
import online.dingod.dindowsandroid.R;

public class TestActivity extends DingBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}