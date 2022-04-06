package online.dingod.dingui.tab.top;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import online.dingod.dingui.tab.bottom.DingTabBottom;
import online.dingod.dingui.tab.bottom.DingTabBottomInfo;
import online.dingod.dingui.tab.common.IDingTabLayout;

public class DingTabTopLayout extends HorizontalScrollView implements IDingTabLayout<DingTabTop, DingTabTopInfo<?>> {

    private List<OnTabSelectedListener<DingTabTopInfo<?>>> tabSelectedChangeListeners = new ArrayList<>();
    private DingTabTopInfo<?> selectedInfo;
    private List<DingTabTopInfo<?>> infoList;

    public DingTabTopLayout(Context context) {
        this(context, null);
    }

    public DingTabTopLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DingTabTopLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setVerticalScrollBarEnabled(false);
    }

    @Override
    public DingTabTop findTab(@NonNull DingTabTopInfo<?> info) {

        ViewGroup rootLayout = getRootLayout(false);
        for (int i = 0; i < rootLayout.getChildCount(); i++) {
            View child = rootLayout.getChildAt(i);
            if (child instanceof DingTabTop) {
                DingTabTop tab = (DingTabTop) child;
                if (tab.getTabBottomInfo() == info) {
                    return tab;
                }
            }
        }
        return null;
    }

    @Override
    public void addTabSelectedChangeListener(OnTabSelectedListener<DingTabTopInfo<?>> listener) {
        tabSelectedChangeListeners.add(listener);
    }

    @Override
    public void defaultSelected(@NonNull DingTabTopInfo<?> defaultInfo) {
        onSelected(defaultInfo);
    }

    @Override
    public void inflateInfo(@NonNull List<DingTabTopInfo<?>> infoList) {

        if (infoList.isEmpty()) {
            return;
        }
        this.infoList = infoList;
        LinearLayout rootLayout = getRootLayout(true);
        selectedInfo = null;
        Iterator<OnTabSelectedListener<DingTabTopInfo<?>>> iterator = tabSelectedChangeListeners.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof DingTabTop) {
                iterator.remove();
            }
        }
        for (int i = 0; i < infoList.size(); i++) {
            DingTabTopInfo<?> info = infoList.get(i);
            DingTabTop tab = new DingTabTop(getContext());
            tabSelectedChangeListeners.add(tab);
            tab.setDingTabInfo(info);
            rootLayout.addView(tab);
            tab.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSelected(info);
                }
            });
        }
    }

    private LinearLayout getRootLayout(boolean clear) {
        LinearLayout rootView = (LinearLayout) getChildAt(0);
        if (rootView == null) {
            rootView = new LinearLayout(getContext());
            rootView.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            addView(rootView, params);
        } else if (clear) {
            rootView.removeAllViews();
        }
        return rootView;
    }

    private void onSelected(@NonNull DingTabTopInfo<?> nextInfo) {
        for (OnTabSelectedListener<DingTabTopInfo<?>> listener : tabSelectedChangeListeners) {
            listener.onTabSelectedChange(infoList.indexOf(nextInfo), selectedInfo, nextInfo);
        }
        this.selectedInfo = nextInfo;
    }
}
