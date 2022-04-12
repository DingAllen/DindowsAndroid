package online.dingod.dingui.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import online.dingod.dingui.R;

public class DingTextOverView extends DingOverView{

    private TextView mText;
    private View mRotateView;

    public DingTextOverView(@NonNull Context context) {
        super(context);
    }

    public DingTextOverView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DingTextOverView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.ding_text_overview, this, true);
        mText = findViewById(R.id.tv_text);
        mRotateView = findViewById(R.id.iv_rotate);
    }

    @Override
    protected void onScroll(int scrollY, int PullRefreshHeight) {

    }

    @Override
    protected void onVisable() {
        mText.setText(R.string.text_visable);
    }

    @Override
    public void onOver() {
        mText.setText(R.string.text_over);
    }

    @Override
    public void onRefresh() {
        mText.setText(R.string.text_refresh);
        Animation operatingAnim = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anim);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        mRotateView.startAnimation(operatingAnim);
    }

    @Override
    public void onFinish() {
        mText.setText(R.string.text_finish);
        mRotateView.clearAnimation();
    }
}
