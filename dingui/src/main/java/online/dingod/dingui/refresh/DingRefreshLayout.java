package online.dingod.dingui.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DingRefreshLayout extends FrameLayout implements DingRefresh {

    private DingOverView.DingRefreshState mState;
    private GestureDetector mGestureDetector;
    private DingRefresh.DingRefreshListener mDingRefreshListener;
    protected DingOverView mDingOverView;
    private int mLastY;
    private boolean disableRefreshScroll;
    private AutoScroller mAutoScroller;

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
        mAutoScroller = new AutoScroller();
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
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        View head = getChildAt(0);
        if (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_CANCEL || ev.getAction() == MotionEvent.ACTION_POINTER_INDEX_MASK) {
            // 松开手
            if (head.getBottom() > 0) {
                if (mState != DingOverView.DingRefreshState.STATE_REFRESH) {
                    recover(head.getBottom());
                    return false;
                }
            }
            mLastY = 0;
        }
        boolean consumed = mGestureDetector.onTouchEvent(ev);
        if ((consumed || (mState != DingOverView.DingRefreshState.STATE_INIT && mState != DingOverView.DingRefreshState.STATE_REFRESH)) && head.getBottom() != 0) {
            ev.setAction(MotionEvent.ACTION_CANCEL);
            return super.dispatchTouchEvent(ev);
        }
        if (consumed) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void recover(int distance) {
        if (mDingRefreshListener != null && distance > mDingOverView.mPullRefreshHeight) {
            mAutoScroller.recover(distance - mDingOverView.mPullRefreshHeight);
            mState = DingOverView.DingRefreshState.STATE_OVER_RELEASE;
        } else {
            mAutoScroller.recover(distance);
        }
    }

    /**
     * 借助Scroller实现视图的自动滚动
     */
    private class AutoScroller implements Runnable {

        private Scroller mScroller;
        private int mLastY;
        private boolean mIsFinished;

        public AutoScroller() {
            mScroller = new Scroller(getContext(), new LinearInterpolator());
            mIsFinished = true;
        }

        @Override
        public void run() {
            if (mScroller.computeScrollOffset()) { // 还未滚动完成
                // TODO: moveDown
                mLastY = mScroller.getCurrY();
                post(this);
            } else {
                removeCallbacks(this);
                mIsFinished = true;
            }
        }

        void recover(int distance) {
            if (distance < 0) {
                return;
            }
            removeCallbacks(this);
            mLastY = 0;
            mIsFinished = false;
            mScroller.startScroll(0, 0, 0, distance, 300);
            post(this);
        }

        boolean isFinished() {
            return mIsFinished;
        }
    }

}
