package online.dingod.dingui.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DingRefreshLayout extends FrameLayout implements DingRefresh {

    private DingOverView.DingRefreshState mState;
    private GestureDetector mGestureDetector;
    private DingRefresh.DingRefreshListener mDingRefreshListener;
    protected DingOverView mDingOverView;
    private int mLastY;
    private boolean disableRefreshScroll;

    public DingRefreshLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public DingRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DingRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mGestureDetector = new GestureDetector(getContext(), dingGestureDetector);
    }

    @Override
    public void setDisableRefreshScroll(boolean disableRefreshScroll) {
        this.disableRefreshScroll = disableRefreshScroll;
    }

    @Override
    public void refreshFinished() {

    }

    @Override
    public void setRefreshListener(DingRefreshListener dingRefreshListener) {
        mDingRefreshListener = dingRefreshListener;
    }

    @Override
    public void setRefreshOverView(DingOverView dingOverView) {
        mDingOverView = dingOverView;
    }

    DingGestureDetector dingGestureDetector = new DingGestureDetector() {
        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return super.onScroll(motionEvent, motionEvent1, v, v1);
        }
    };
}
