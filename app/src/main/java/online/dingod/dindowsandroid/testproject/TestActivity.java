package online.dingod.dindowsandroid.testproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import online.dingod.common.ui.component.DingBaseActivity;
import online.dingod.dindowsandroid.R;
import online.dingod.dindowsandroid.testproject.logic.TestActivityLogic;

public class TestActivity extends DingBaseActivity implements TestActivityLogic.ActivityProvider {

    private TestActivityLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        logic = new TestActivityLogic(this, savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        logic.onSaveInstanceState(outState);
    }
}