package online.dingod.dingui.banner.core;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

/**
 * 实现自动翻页的ViewPager
 */
public class DingViewPager extends ViewPager {

    private int mIntervalTime;
    private boolean mAutoPlay = true;
    private boolean isLayout;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            // TODO: 切换到下一个
            next();
            mHandler.postDelayed(this, mIntervalTime);
        }
    };

    public DingViewPager(@NonNull Context context) {
        super(context);
    }

    public DingViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAutoPlay(boolean autoPlay) {
        mAutoPlay = autoPlay;
        if (!mAutoPlay) {
            mHandler.removeCallbacksAndMessages(mRunnable);
        }
    }

    public void setIntervalTime(int intervalTime) {
        mIntervalTime = intervalTime;
    }

    public void stop() {
        mHandler.removeCallbacksAndMessages(null);
    }

    public void start() {
        mHandler.removeCallbacksAndMessages(null);
        if (mAutoPlay) {
            mHandler.postDelayed(mRunnable, mIntervalTime);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                start();
                break;
            default:
                stop();
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isLayout && getAdapter() != null && getAdapter().getCount() > 0) {
            try {
                Field mScroller = ViewPager.class.getDeclaredField("mFirstLayout");
                mScroller.setAccessible(true);
                mScroller.set(this, false);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (((Activity) getContext()).isFinishing()) {
            super.onDetachedFromWindow();
        }
        stop();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        isLayout = true;
    }

    /**
     * 设置下一个要显示的item，并返回item的position
     *
     * @return 下一个要显示item的position
     */
    private int next() {
        int nextPosition = -1;
        if (getAdapter() == null || getAdapter().getCount() <= 1) {
            stop();
            return nextPosition;
        }
        nextPosition = getCurrentItem() + 1;
        if (nextPosition >= getAdapter().getCount()) {
            nextPosition = ((DingBannerAdapter) getAdapter()).getFirstItemIndex();
        }
        setCurrentItem(nextPosition, true);
        return nextPosition;
    }
}
