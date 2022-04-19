package online.dingod.dingui.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import org.w3c.dom.Attr;

import java.util.List;

import online.dingod.dingui.R;
import online.dingod.dingui.banner.core.DingBannerDelegate;
import online.dingod.dingui.banner.core.DingBannerMo;
import online.dingod.dingui.banner.core.IBindAdapter;
import online.dingod.dingui.banner.core.IDingBanner;
import online.dingod.dingui.banner.indicator.DingIndicator;

public class DingBanner extends FrameLayout implements IDingBanner {

    private DingBannerDelegate delegate;

    public DingBanner(@NonNull Context context) {
        this(context, null);
    }

    public DingBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DingBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        delegate = new DingBannerDelegate(context, this);
        initCustomAttrs(context, attrs);
    }

    private void initCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DingBanner);
        boolean autoplay = typedArray.getBoolean(R.styleable.DingBanner_autoplay, true);
        boolean loop = typedArray.getBoolean(R.styleable.DingBanner_loop, true);
        int intervalTime = typedArray.getInt(R.styleable.DingBanner_intervalTime, -1);
        setAutoplay(autoplay);
        setLoop(loop);
        setIntervalTime(intervalTime);
        typedArray.recycle();
    }

    @Override
    public void setBannerData(int layoutResId, @NonNull List<? extends DingBannerMo> models) {
        delegate.setBannerData(layoutResId, models);
    }

    @Override
    public void setBannerData(@NonNull List<? extends DingBannerMo> models) {
        delegate.setBannerData(models);
    }

    @Override
    public void setDingIndicator(DingIndicator dingIndicator) {
        delegate.setDingIndicator(dingIndicator);
    }

    @Override
    public void setAutoplay(boolean autoplay) {
        delegate.setAutoplay(autoplay);
    }

    @Override
    public void setLoop(boolean loop) {
        delegate.setLoop(loop);
    }

    @Override
    public void setIntervalTime(int intervalTime) {
        delegate.setIntervalTime(intervalTime);
    }

    @Override
    public void setBindAdapter(IBindAdapter bindAdapter) {
        delegate.setBindAdapter(bindAdapter);
    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        delegate.setOnPageChangeListener(onPageChangeListener);
    }

    @Override
    public void setOnBannerCLickListener(OnBannerClickListener onBannerClickListener) {
        delegate.setOnBannerCLickListener(onBannerClickListener);
    }
}
