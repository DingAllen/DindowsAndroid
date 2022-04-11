package online.dingod.dingui.refresh;

public interface DingRefresh {

    /**
     * 刷新时是否禁止滚动
     * @param disableRefreshScroll 是否禁止滚动
     */
    void setDisableRefreshScroll(boolean disableRefreshScroll);

    /**
     * 刷新完成
     */
    void refreshFinished();

    /**
     * 设置下拉刷新的监听器
     * @param dingRefreshListener 监听器
     */
    void setRefreshListener(DingRefreshListener dingRefreshListener);

    /**
     * 设置下拉刷新的视图
     * @param dingOverView 视图
     */
    void setRefreshOverView(DingOverView dingOverView);

    interface DingRefreshListener{
        void onRefresh();

        boolean enableRefresh();
    }
}
