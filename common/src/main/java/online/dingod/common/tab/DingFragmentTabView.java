package online.dingod.common.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DingFragmentTabView extends FrameLayout {

    private DingTabViewAdapter tabViewAdapter;
    private int currentPosition;

    public DingFragmentTabView(@NonNull Context context) {
        super(context);
    }

    public DingFragmentTabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DingFragmentTabView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DingTabViewAdapter getTabViewAdapter() {
        return tabViewAdapter;
    }

    public void setTabViewAdapter(DingTabViewAdapter tabViewAdapter) {
        if (this.tabViewAdapter != null || tabViewAdapter == null) {
            return;
        }
        this.tabViewAdapter = tabViewAdapter;
        currentPosition = -1;
    }

    public void setCurrentItem(int position) {
        if (position < 0 || position >= tabViewAdapter.getCount()) {
            return;
        }
        if (currentPosition != position) {
            currentPosition = position;
            tabViewAdapter.instantiateItem(this, position);
        }
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public Fragment getCurrentFragment() {
        if (this.tabViewAdapter == null) {
            throw new IllegalArgumentException("Please call setAdapter at first.");
        }
        return this.tabViewAdapter.getCurrentFragment();
    }
}
