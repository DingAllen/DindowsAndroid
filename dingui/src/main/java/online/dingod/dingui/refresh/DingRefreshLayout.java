package online.dingod.dingui.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        // 定义head和child的排列位置
        View head = getChildAt(0);
        View child = getChildAt(1);

        if (head != null && child != null) {
            int childTop = child.getTop();
            if (mState == DingOverView.DingRefreshState.STATE_REFRESH) {
                head.layout(0, mDingOverView.mPullRefreshHeight - head.getMeasuredHeight(), right, mDingOverView.mPullRefreshHeight);
                child.layout(0, mDingOverView.mPullRefreshHeight, right, mDingOverView.mPullRefreshHeight + child.getMeasuredHeight());
            } else {
                head.layout(0, childTop - head.getMeasuredHeight(), right, childTop);
                child.layout(0, childTop, right, childTop + child.getMeasuredHeight());
            }
            View other;
            for (int i = 2; i < getChildCount(); i++) {
                other = getChildAt(i);
                other.layout(0, top, right, bottom);
            }
        }
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
        View head = getChildAt(0);
        mDingOverView.onFinish();
        mDingOverView.setState(DingOverView.DingRefreshState.STATE_INIT);
        int bottom = head.getBottom();
        if (bottom > 0) {
            recover(bottom);
        }
        mState = DingOverView.DingRefreshState.STATE_INIT;
    }

    @Override
    public void setRefreshListener(DingRefreshListener dingRefreshListener) {
        mDingRefreshListener = dingRefreshListener;
    }

    @Override
    public void setRefreshOverView(DingOverView dingOverView) {
        if (this.mDingOverView != null) {
            removeView(mDingOverView);
        }
        mDingOverView = dingOverView;
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mDingOverView, 0, params);
    }

    DingGestureDetector dingGestureDetector = new DingGestureDetector() {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            if (Math.abs(distanceX) > Math.abs(distanceY) || mDingRefreshListener != null && !mDingRefreshListener.enableRefresh()) {
                return false;
            }

            if (disableRefreshScroll && mState == DingOverView.DingRefreshState.STATE_REFRESH) { // 刷新时是否禁止滚动
                return true;
            }

            View head = getChildAt(0);
            View child = DingScrollUtil.findScrollableChild(DingRefreshLayout.this);

            if (DingScrollUtil.childScrolled(child)) { // 如果滚动发生在子视图中，则不处理
                return false;
            }

            // 没有刷新或没有达到可以刷新的距离，且头部已经划出或下拉
            if ((mState != DingOverView.DingRefreshState.STATE_REFRESH || head.getBottom() <= mDingOverView.mPullRefreshHeight) && (head.getBottom() > 0 || distanceY <= 0.0f)) {
                // 还在滑动中
                if (mState != DingOverView.DingRefreshState.STATE_OVER_RELEASE) {
                    int offset;
                    if (child.getTop() < mDingOverView.mPullRefreshHeight) {
                        offset = (int) (mLastY / mDingOverView.minDamp);
                    } else {
                        offset = (int) (mLastY / mDingOverView.maxDamp);
                    }
                    // 如果是正在刷新的状态，则不允许在滑动的时候改变状态
                    boolean bool = moveDown(offset, true);
                    mLastY = (int) -distanceY;
                    return bool;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    };

    /**
     * 根据偏移量移动header和child
     *
     * @param offsetY 偏移量
     * @param nonAuto 是否自动触发滚动
     * @return
     */
    private boolean moveDown(int offsetY, boolean nonAuto) {

        View head = getChildAt(0);
        View child = getChildAt(1);
        int childTop = child.getTop() + offsetY;
        if (childTop <= 0) { // 异常情况
            offsetY = -child.getTop();
            // 移动head和child的位置到原始位置
            head.offsetTopAndBottom(offsetY);
            child.offsetTopAndBottom(offsetY);
            if (mState != DingOverView.DingRefreshState.STATE_REFRESH) {
                mState = DingOverView.DingRefreshState.STATE_INIT;
            }
        } else if (mState == DingOverView.DingRefreshState.STATE_REFRESH && childTop > mDingOverView.mPullRefreshHeight) {
            // 如果正在下拉刷新中，禁止继续下拉
            return false;
        } else if (childTop <= mDingOverView.mPullRefreshHeight) {
            if (mDingOverView.getState() != DingOverView.DingRefreshState.STATE_VISABLE && nonAuto) {
                mDingOverView.onVisable();
                mDingOverView.setState(DingOverView.DingRefreshState.STATE_VISABLE);
                mState = DingOverView.DingRefreshState.STATE_VISABLE;
            }
            head.offsetTopAndBottom(offsetY);
            child.offsetTopAndBottom(offsetY);
            if (childTop == mDingOverView.mPullRefreshHeight && mState == DingOverView.DingRefreshState.STATE_OVER_RELEASE) {
                refresh();
            }
        } else {
            if (mDingOverView.getState() != DingOverView.DingRefreshState.STATE_REFRESH && nonAuto) {
                // 超出刷新位置
                mDingOverView.onOver();
                mDingOverView.setState(DingOverView.DingRefreshState.STATE_OVER);
            }
            head.offsetTopAndBottom(offsetY);
            child.offsetTopAndBottom(offsetY);
        }
        if (mDingOverView != null) {
            mDingOverView.onScroll(head.getBottom(), mDingOverView.mPullRefreshHeight);
        }
        return true;
    }

    /**
     * 刷新
     */
    private void refresh() {
        if (mDingRefreshListener != null) {
            mState = DingOverView.DingRefreshState.STATE_REFRESH;
            mDingOverView.onRefresh();
            mDingOverView.setState(DingOverView.DingRefreshState.STATE_REFRESH);
            mDingRefreshListener.onRefresh();
        }
    }

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
            if (mScroller.computeScrollOffset()) {
                moveDown(mLastY - mScroller.getCurrY(), false);
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
