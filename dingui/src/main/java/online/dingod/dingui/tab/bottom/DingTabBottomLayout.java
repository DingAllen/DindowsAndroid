package online.dingod.dingui.tab.bottom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import online.dingod.dingui.tab.common.IDingTabLayout;

public class DingTabBottomLayout extends FrameLayout implements IDingTabLayout<DingTabBottom, DingTabBottomInfo<?>> {

    private List<OnTabSelectedListener<DingTabBottomInfo<?>>> tabSelectedChangeListeners = new ArrayList<>();
    private DingTabBottomInfo<?> selectedInfo;
    private float bottomAlpha = 1f;
    private float tabBottomHeight = 50;
    private float bottomLineHeight = 0.5f;
    private String bottomLineColor = "#dfe0e1";
    private List<DingTabBottomInfo<?>> infoList;

    public DingTabBottomLayout(@NonNull Context context) {
        super(context);
    }

    public DingTabBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DingTabBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public DingTabBottom findTab(@NonNull DingTabBottomInfo<?> data) {
        return null;
    }

    @Override
    public void addTabSelectedChangeListener(OnTabSelectedListener<DingTabBottomInfo<?>> listener) {

    }

    @Override
    public void defaultSelected(@NonNull DingTabBottomInfo<?> defaultInfo) {

    }

    @Override
    public void inflateInfo(@NonNull List<DingTabBottomInfo<?>> infoList) {

        if (infoList.isEmpty()) {
            return;
        }
        this.infoList = infoList;

        // 移除之前已经添加的Tab
        for (int i = getChildCount() - 1; i > 0; i--) {
            removeViewAt(i);
        }
        selectedInfo = null;
    }

    private void addBackground() {

    }
}
