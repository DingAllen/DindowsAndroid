package online.dingod.dingui.tab.top;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import online.dingod.dingui.R;
import online.dingod.dingui.tab.common.IDingTab;

public class DingTabTop extends RelativeLayout implements IDingTab<DingTabTopInfo<?>> {

    private DingTabTopInfo<?> tabBottomInfo;
    private ImageView mIvImage;
    private TextView mTvName;
    private View indicator;

    public DingTabTop(Context context) {
        this(context, null);
    }

    public DingTabTop(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DingTabTop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DingTabTopInfo<?> getTabBottomInfo() {
        return tabBottomInfo;
    }

    public ImageView getmIvImage() {
        return mIvImage;
    }

    public TextView getmTvName() {
        return mTvName;
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.ding_tab_top, this);
        mIvImage = findViewById(R.id.iv_image);
        mTvName = findViewById(R.id.tv_name);
        indicator = findViewById(R.id.tab_top_indicator);
    }

    private void inflateInfo(boolean selected, boolean init) {

        if (tabBottomInfo.tabType == DingTabTopInfo.TabType.TEXT) {
            if (init) {
                mIvImage.setVisibility(GONE);
                mTvName.setVisibility(VISIBLE);
                if (!TextUtils.isEmpty(tabBottomInfo.name)) {
                    mTvName.setText(tabBottomInfo.name);
                }
            }
            if (selected) {
                indicator.setVisibility(VISIBLE);
                mTvName.setTextColor(getTextColor(tabBottomInfo.tintColor));
            } else {
                indicator.setVisibility(GONE);
                mTvName.setTextColor(getTextColor(tabBottomInfo.defaultColor));
            }
        } else if (tabBottomInfo.tabType == DingTabTopInfo.TabType.BITMAP) {
            if (init) {
                indicator.setVisibility(GONE);
                mTvName.setVisibility(GONE);
                mIvImage.setVisibility(VISIBLE);
            }
            if (selected) {
                mIvImage.setImageBitmap(tabBottomInfo.selectedBitmap);
            } else {
                mIvImage.setImageBitmap(tabBottomInfo.defaultBitmap);
            }
        }
    }

    @Override
    public void setDingTabInfo(@NonNull DingTabTopInfo<?> data) {
        this.tabBottomInfo = data;
        inflateInfo(false, true);
    }

    @Override
    public void resetHeight(int height) {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = height;
        setLayoutParams(params);
        getmTvName().setVisibility(GONE);
    }

    @Override
    public void onTabSelectedChange(int index, @Nullable DingTabTopInfo<?> prevInfo, @NonNull DingTabTopInfo<?> nextInfo) {
        if (prevInfo != tabBottomInfo && nextInfo != tabBottomInfo || prevInfo == nextInfo) {
            return;
        }
        inflateInfo(prevInfo != tabBottomInfo, false);
    }

    @ColorInt
    private int getTextColor(Object color) {
        if (color instanceof String) {
            return Color.parseColor((String) color);
        } else {
            return (int) color;
        }
    }
}
