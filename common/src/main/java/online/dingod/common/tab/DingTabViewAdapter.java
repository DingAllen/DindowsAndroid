package online.dingod.common.tab;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import online.dingod.dingui.tab.bottom.DingTabBottomInfo;

public class DingTabViewAdapter {

    private List<DingTabBottomInfo<?>> infoList;
    private Fragment currentFragment;
    private FragmentManager fragmentManager;

    public DingTabViewAdapter(FragmentManager fragmentManager, List<DingTabBottomInfo<?>> infoList) {
        this.infoList = infoList;
        this.fragmentManager = fragmentManager;
    }

    /**
     * 实例化以及显示指定位置的Fragment
     * @param container
     * @param position
     */
    public void instantiateItem(View container, int position) {
        FragmentTransaction currentTransaction = fragmentManager.beginTransaction();
        if (currentFragment != null) {
            currentTransaction.hide(currentFragment);
        }
        String name = container.getId() + ":" + position;
        Fragment fragment = fragmentManager.findFragmentByTag(name);
        if (fragment != null) {
            currentTransaction.show(fragment);
        } else {
            fragment = getItem(position);
            if (!fragment.isAdded()) {
                currentTransaction.add(container.getId(), fragment, name);
            }
        }
        currentFragment = fragment;
        currentTransaction.commitAllowingStateLoss();
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    public Fragment getItem(int position) {
        try {
            return infoList.get(position).fragment.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getCount() {
        return infoList == null ? 0 : infoList.size();
    }
}
