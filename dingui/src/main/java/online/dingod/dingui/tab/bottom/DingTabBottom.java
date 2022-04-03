package online.dingod.dingui.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import online.dingod.dingui.R;
import online.dingod.dingui.tab.common.IDingTab;

public class DingTabBottom extends RelativeLayout implements IDingTab<DingTabBottomInfo<?>> {

    private DingTabBottomInfo<?> tabBottomInfo;
    private ImageView mIvImage;
    private TextView mTvIcon;
    private TextView mTvName;

    public DingTabBottom(Context context) {
        this(context, null);
    }

    public DingTabBottom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DingTabBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DingTabBottomInfo<?> getTabBottomInfo() {
        return tabBottomInfo;
    }

    public ImageView getmIvImage() {
        return mIvImage;
    }

    public TextView getmTvIcon() {
        return mTvIcon;
    }

    public TextView getmTvName() {
        return mTvName;
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.ding_tab_bottom, this);
        mIvImage = findViewById(R.id.iv_image);
        mTvIcon = findViewById(R.id.tv_icon);
        mTvName = findViewById(R.id.tv_name);
    }

    private void inflateInfo(boolean selected, boolean init) {

        if (tabBottomInfo.tabType == DingTabBottomInfo.TabType.ICON) {
            if (init) {
                mIvImage.setVisibility(GONE);
                mTvIcon.setVisibility(VISIBLE);
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), tabBottomInfo.iconFont);
                mTvIcon.setTypeface(typeface);
                if (!TextUtils.isEmpty(tabBottomInfo.name)) {
                    mTvName.setText(tabBottomInfo.name);
                }
            }
            if (selected) {
                mTvIcon.setText(TextUtils.isEmpty(tabBottomInfo.selectedIconName) ? tabBottomInfo.defaultIconName : tabBottomInfo.selectedIconName);
                mTvIcon.setTextColor(getTextColor(tabBottomInfo.tintColor));
                mTvName.setTextColor(getTextColor(tabBottomInfo.tintColor));
            } else {
                mTvIcon.setText(tabBottomInfo.defaultIconName);
                mTvIcon.setTextColor(getTextColor(tabBottomInfo.defaultColor));
                mTvName.setTextColor(getTextColor(tabBottomInfo.defaultColor));
            }
        } else if (tabBottomInfo.tabType == DingTabBottomInfo.TabType.BITMAP) {
            if (init) {
                mTvIcon.setVisibility(GONE);
                mIvImage.setVisibility(VISIBLE);
                if (!TextUtils.isEmpty(tabBottomInfo.name)) {
                    mTvName.setText(tabBottomInfo.name);
                }
            }
            if (selected) {
                mIvImage.setImageBitmap(tabBottomInfo.selectedBitmap);
            } else {
                mIvImage.setImageBitmap(tabBottomInfo.defaultBitmap);
            }
        }
    }

    @Override
    public void setDingTabInfo(@NonNull DingTabBottomInfo<?> data) {
        this.tabBottomInfo = data;
        inflateInfo(false, true);
    }

    @Override
    public void resetHeight(int height) {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = height;
        setLayoutParams(params);
        getmIvImage().setVisibility(GONE);
    }

    @Override
    public void onTabSelectedChange(int index, @Nullable DingTabBottomInfo<?> prevInfo, @NonNull DingTabBottomInfo<?> nextInfo) {
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
