package online.dingod.dingui.banner.core;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class DingBannerAdapter extends PagerAdapter {

    private Context mContext;
    private SparseArray<DingBannerViewHolder> mCachedViews = new SparseArray<>();
    private IDingBanner.OnBannerClickListener mBannerClickListener;
    private IBindAdapter mBindAdapter;
    private List<? extends DingBannerMo> models;
    private boolean mAutoplay = true;
    private boolean mLoop = false;
    private int mLayoutResId = -1;

    public DingBannerAdapter(Context context) {
        mContext = context;
    }

    public void setBannerData(@NonNull List<? extends DingBannerMo> models) {
        this.models = models;
        // 初始化数据
        initCachedView();
        notifyDataSetChanged();
    }

    public void setBindAdapter(IBindAdapter bindAdapter) {
        mBindAdapter = bindAdapter;
    }

    public void setOnBannerClickListener(IDingBanner.OnBannerClickListener onBannerClickListener) {
        mBannerClickListener = onBannerClickListener;
    }

    public void setAutoplay(boolean autoplay) {
        mAutoplay = autoplay;
    }

    public void setLoop(boolean loop) {
        mLoop = loop;
    }

    public int getRealCount() {
        return models == null ? 0 : models.size();
    }

    public int getFirstItemIndex() {
        return Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2) % getRealCount();
    }

    @Override
    public int getCount() {
        return mAutoplay ? Integer.MAX_VALUE : (mLoop ? Integer.MAX_VALUE : getRealCount());
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        int realPosition = position;
        if (getRealCount() > 0) {
            realPosition = position % getRealCount();
        }
        DingBannerViewHolder viewHolder = mCachedViews.get(realPosition);
        if (container.equals(viewHolder.rootView.getParent())) {
            container.removeView(viewHolder.rootView);
        }
        if (viewHolder.rootView.getParent() != null) {
            ((ViewGroup) viewHolder.rootView.getParent()).removeView(viewHolder.rootView);
        }
        container.addView(viewHolder.rootView);
        return viewHolder.rootView;
    }



    private void initCachedView() {
        mCachedViews = new SparseArray<>();
        for (int i = 0; i < models.size(); i++) {
            DingBannerViewHolder viewHolder = new DingBannerViewHolder(createView(LayoutInflater.from(mContext), null));
            mCachedViews.put(i, viewHolder);
        }
    }

    private View createView(LayoutInflater layoutInflater, ViewGroup parent) {
        if (mLayoutResId == -1) {
            throw new IllegalArgumentException("你得先初始化Layout的ResId");
        }
        return layoutInflater.inflate(mLayoutResId, parent, false);
    }

    public static class DingBannerViewHolder {
        View rootView;

        public DingBannerViewHolder(View rootView) {
            this.rootView = rootView;
        }
    }

}
