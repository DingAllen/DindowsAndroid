package online.dingod.dingui.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 下拉刷新的视图，可以通过重载这个类实现自定义刷新视图
 */
public abstract class DingOverView extends FrameLayout {

    public enum DingRefreshState {
        /**
         * 初始状态
         */
        STATE_INIT,
        /**
         * Header展示的状态
         */
        STATE_VISABLE,
        /**
         * 超出可刷新距离的状态
         */
        STATE_REFRESH,
        /**
         * 超出刷新位置松开手后的状态
         */
        STATE_OVER_RELEASE
    }

    protected DingRefreshState mState = DingRefreshState.STATE_INIT;

    /**
     * 触发下拉刷新需要的最小高度
     */
    public int mPullRefreshHeight;

    /**
     * 最小阻尼
     */
    public float minDamp = 1.6f;

    /**
     * 最大阻尼
     */
    public float maxDamp = 2.2f;


    public DingOverView(@NonNull Context context) {
        super(context);
    }

    public DingOverView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DingOverView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    public abstract void init();

    protected abstract void onScroll(int scrollY, int PullRefreshHeight);

    /**
     * 显示视图层的回调方法
     */
    protected abstract void onVisable();

    /**
     * 超过可显示视图后，只要手指释放，就会加载此函数
     */
    public abstract void onOver();

    /**
     * 开始刷新
     */
    public abstract void onRefresh();

    /**
     * 刷新完成
     */
    public abstract void onFinish();

    /**
     * 设置状态
     * @param State 状态
     */
    public void setState(DingRefreshState State) {
        mState = State;
    }

    /**
     * 获取状态
     * @return 状态
     */
    public DingRefreshState getState() {
        return mState;
    }
}
