package online.dingod.dingui.banner.core;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import online.dingod.dingui.banner.indicator.DingIndicator;

public interface IDingBanner {

    void setBannerData(@LayoutRes int layoutResId, @NonNull List<? extends DingBannerMo> models);

    void setBannerData(@NonNull List<? extends DingBannerMo> models);

    void setDingIndicator(DingIndicator dingIndicator);

    void setAutoplay(boolean autoplay);

    void setLoop(boolean loop);

    void setIntervalTime(int intervalTime);

    void setBindAdapter(IBindAdapter bindAdapter);

    void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener);

    void setOnBannerCLickListener(OnBannerClickListener onBannerClickListener);

    interface OnBannerClickListener {
        void onBannerClick(@NonNull DingBannerAdapter.DingBannerViewHolder viewHolder, @NonNull DingBannerMo bannerMo);
    }
}
