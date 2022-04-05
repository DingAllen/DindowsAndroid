package online.dingod.dindowsandroid.testproject.logic;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import online.dingod.common.tab.DingFragmentTabView;
import online.dingod.common.tab.DingTabViewAdapter;
import online.dingod.dindowsandroid.R;
import online.dingod.dindowsandroid.testproject.fragment.ChatPageFragment;
import online.dingod.dindowsandroid.testproject.fragment.CollectionPageFragment;
import online.dingod.dindowsandroid.testproject.fragment.HomePageFragment;
import online.dingod.dindowsandroid.testproject.fragment.ProfilePageFragment;
import online.dingod.dinglibrary.util.DingDisplayUtil;
import online.dingod.dingui.tab.bottom.DingTabBottom;
import online.dingod.dingui.tab.bottom.DingTabBottomInfo;
import online.dingod.dingui.tab.bottom.DingTabBottomLayout;
import online.dingod.dingui.tab.common.IDingTabLayout;

public class TestActivityLogic {

    private DingFragmentTabView fragmentTabView;
    private DingTabBottomLayout tabBottomLayout;
    private List<DingTabBottomInfo<?>> infoList;
    private ActivityProvider activityProvider;
    private int currentItemIndex = 0;

    private final static String SAVED_CURRENT_ID = "SAVED_CURRENT_ID";

    public TestActivityLogic(ActivityProvider activityProvider, Bundle savedInstanceState) {
        this.activityProvider = activityProvider;
        if (savedInstanceState != null) {
            currentItemIndex = savedInstanceState.getInt(SAVED_CURRENT_ID);
        }
        initTabBottom();
    }

    public DingFragmentTabView getFragmentTabView() {
        return fragmentTabView;
    }

    public DingTabBottomLayout getTabBottomLayout() {
        return tabBottomLayout;
    }

    public List<DingTabBottomInfo<?>> getInfoList() {
        return infoList;
    }

    private void initTabBottom() {
        tabBottomLayout = activityProvider.findViewById(R.id.tab_bottom_layout);
        tabBottomLayout.setTabAlpha(0.85f);
        infoList = new ArrayList<>();
        int defaultColor = activityProvider.getResources().getColor(R.color.tabBottomDefaultColor);
        int tintColor = activityProvider.getResources().getColor(R.color.tabBottomTintColor);
        DingTabBottomInfo<Integer> homeInfo = new DingTabBottomInfo<Integer>(
                "首页",
                "font/iconfont.ttf",
                activityProvider.getString(R.string.if_home),
                null,
                defaultColor,
                tintColor
        );
        homeInfo.fragment = HomePageFragment.class;
        DingTabBottomInfo<Integer> collectionInfo = new DingTabBottomInfo<Integer>(
                "收藏",
                "font/iconfont.ttf",
                activityProvider.getString(R.string.if_collection),
                null,
                defaultColor,
                tintColor
        );
        collectionInfo.fragment = CollectionPageFragment.class;
        DingTabBottomInfo<Integer> chatInfo = new DingTabBottomInfo<Integer>(
                "聊天",
                "font/iconfont.ttf",
                activityProvider.getString(R.string.if_chat),
                null,
                defaultColor,
                tintColor
        );
        chatInfo.fragment = ChatPageFragment.class;
        DingTabBottomInfo<Integer> profileInfo = new DingTabBottomInfo<Integer>(
                "我的",
                "font/iconfont.ttf",
                activityProvider.getString(R.string.if_profile),
                null,
                defaultColor,
                tintColor
        );
        profileInfo.fragment = ProfilePageFragment.class;

        Bitmap bitmap1 = BitmapFactory.decodeResource(activityProvider.getResources(), R.drawable.gift, null);
        Bitmap bitmap2 =
                BitmapFactory.decodeResource(activityProvider.getResources(), R.drawable.heart, null);
        DingTabBottomInfo<String> bitmapInfo = new DingTabBottomInfo<>(
                "bitmap",
                bitmap1,
                bitmap2
        );
        infoList.add(homeInfo);
        infoList.add(collectionInfo);
        infoList.add(bitmapInfo);
        infoList.add(chatInfo);
        infoList.add(profileInfo);
        tabBottomLayout.inflateInfo(infoList);
        initFragmentTabView();
        tabBottomLayout.addTabSelectedChangeListener(new IDingTabLayout.OnTabSelectedListener<DingTabBottomInfo<?>>() {
            @Override
            public void onTabSelectedChange(int index, @Nullable DingTabBottomInfo<?> prevInfo, @NonNull DingTabBottomInfo<?> nextInfo) {
                fragmentTabView.setCurrentItem(index);
                currentItemIndex = index;
            }
        });
        tabBottomLayout.defaultSelected(infoList.get(currentItemIndex));
        DingTabBottom tabBottom = tabBottomLayout.findTab(infoList.get(2));
        tabBottom.resetHeight(DingDisplayUtil.dp2px(66f, activityProvider.getResources()));
    }

    private void initFragmentTabView() {
        DingTabViewAdapter tabViewAdapter = new DingTabViewAdapter(activityProvider.getSupportFragmentManager(), infoList);
        fragmentTabView = activityProvider.findViewById(R.id.fragment_tab_view);
        fragmentTabView.setTabViewAdapter(tabViewAdapter);
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SAVED_CURRENT_ID, currentItemIndex);
    }


    public interface ActivityProvider {

        <T extends View> T findViewById(@IdRes int id);

        Resources getResources();

        FragmentManager getSupportFragmentManager();

        String getString(@StringRes int resId);
    }
}
