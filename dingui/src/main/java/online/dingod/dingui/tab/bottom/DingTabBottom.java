package online.dingod.dingui.tab.bottom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.hi_tab_bottom, this);
        mIvImage = findViewById(R.id.iv_image);
        mTvIcon = findViewById(R.id.tv_icon);
        mTvName = findViewById(R.id.tv_name);
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

    @Override
    public void setHiTabInfo(@NonNull DingTabBottomInfo<?> data) {

    }

    @Override
    public void resetHeight(int height) {

    }

    @Override
    public void onTabSelectedChange(int index, @Nullable DingTabBottomInfo<?> prevInfo, @NonNull DingTabBottomInfo<?> nextInfo) {

    }
}
