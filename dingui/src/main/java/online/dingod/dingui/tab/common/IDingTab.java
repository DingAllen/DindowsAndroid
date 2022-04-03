package online.dingod.dingui.tab.common;

import androidx.annotation.NonNull;
import androidx.annotation.Px;

public interface IDingTab<D> extends IDingTabLayout.OnTabSelectedListener<D> {

    void setDingTabInfo(@NonNull D data);

    void resetHeight(@Px int height);
}
