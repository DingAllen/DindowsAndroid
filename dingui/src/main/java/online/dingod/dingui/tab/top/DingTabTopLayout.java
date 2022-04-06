package online.dingod.dingui.tab.top;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import online.dingod.dinglibrary.util.DingDisplayUtil;
import online.dingod.dingui.tab.bottom.DingTabBottom;
import online.dingod.dingui.tab.bottom.DingTabBottomInfo;
import online.dingod.dingui.tab.common.IDingTabLayout;

public class DingTabTopLayout extends HorizontalScrollView implements IDingTabLayout<DingTabTop, DingTabTopInfo<?>> {

    private List<OnTabSelectedListener<DingTabTopInfo<?>>> tabSelectedChangeListeners = new ArrayList<>();
    private DingTabTopInfo<?> selectedInfo;
    private List<DingTabTopInfo<?>> infoList;

    private int tabWidth = 0;

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
        autoScroll(nextInfo);
    }

    private void autoScroll(@NonNull DingTabTopInfo<?> nextInfo) {
        DingTabTop tab = findTab(nextInfo);
        if (tab == null) {
            return;
        }
        int index = infoList.indexOf(nextInfo);
        int scrollWidth;
        int[] loc = new int[2];
        tab.getLocationInWindow(loc);
        tabWidth = tab.getWidth();
        if ((loc[0] + tabWidth / 2) > DingDisplayUtil.getDisplayWidthInPx(getContext()) / 2) {
            scrollWidth = rangeScrollWidth(index, 2);
        } else {
            scrollWidth = rangeScrollWidth(index, -2);
        }
        scrollTo(getScrollX() + scrollWidth, 0);
    }

    private int rangeScrollWidth(int index, int range) {
        int scrollWidth = 0;
        for (int i = 0; i <= Math.abs(range); i++) {
            int next;
            if (range < 0) {
                next = range + i + index;
            } else {
                next = range - i + index;
            }
            if (next >= 0 && next < infoList.size()) {
                if (range < 0) {
                    scrollWidth -= scrollWidth(next, false);
                } else {
                    scrollWidth += scrollWidth(next, true);
                }
            }
        }
        return scrollWidth;
    }

    private int scrollWidth(int index, boolean toRight) {
        DingTabTop target = findTab(infoList.get(index));
        if (target == null) {
            return 0;
        }
        Rect rect = new Rect();
        target.getLocalVisibleRect(rect);
        if (toRight) {
            if (rect.right > tabWidth) {
                return tabWidth;
            } else {
                return tabWidth - rect.right;
            }
        } else {
            if (rect.left <= -tabWidth) {
                return tabWidth;
            } else if (rect.left > 0) {
                return rect.left;
            }
            return 0;
        }
    }
}
