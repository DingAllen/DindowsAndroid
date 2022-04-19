package online.dingod.dingui.banner.core;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import online.dingod.dingui.banner.DingBanner;
import online.dingod.dingui.banner.indicator.DingIndicator;

public class DingBannerDelegate implements IDingBanner{

    private Context mContext;
    private DingBanner mDingBanner;

    public DingBannerDelegate(Context context, DingBanner dingBanner) {
        mContext = context;
        mDingBanner = dingBanner;
    }

    @Override
    public void setBannerData(int layoutResId, @NonNull List<? extends DingBannerMo> models) {

    }

    @Override
    public void setBannerData(@NonNull List<? extends DingBannerMo> models) {

    }

    @Override
    public void setDingIndicator(DingIndicator dingIndicator) {

    }

    @Override
    public void setAutoplay(boolean autoplay) {

    }

    @Override
    public void setLoop(boolean loop) {

    }

    @Override
    public void setIntervalTime(int intervalTime) {

    }

    @Override
    public void setBindAdapter(IBindAdapter bindAdapter) {

    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {

    }

    @Override
    public void setOnBannerCLickListener(OnBannerClickListener onBannerClickListener) {

    }
}
