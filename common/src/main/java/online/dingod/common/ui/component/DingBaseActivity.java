package online.dingod.common.ui.component;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import online.dingod.common.R;

public class DingBaseActivity extends AppCompatActivity implements DingBaseActionInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_base);
    }
}