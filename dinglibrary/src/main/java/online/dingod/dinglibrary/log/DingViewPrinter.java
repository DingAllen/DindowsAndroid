package online.dingod.dinglibrary.log;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import online.dingod.dinglibrary.R;

public class DingViewPrinter implements DingLogPrinter{

    private RecyclerView recyclerView;
    private LogAdapter adapter;
    private DingViewPrinterProvider viewProvider;

    public DingViewPrinter(Activity activity) {
        FrameLayout rootView = activity.findViewById(android.R.id.content);
        recyclerView = new RecyclerView(activity);
        adapter = new LogAdapter(LayoutInflater.from(recyclerView.getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        viewProvider = new DingViewPrinterProvider(rootView, recyclerView);
    }

    @NonNull
    public DingViewPrinterProvider getViewProvider() {
        return viewProvider;
    }

    @Override
    public void print(@NonNull DingLogConfig config, int level, String tag, @NonNull String printString) {

        adapter.addItem(new DingLogMo(System.currentTimeMillis(), level, tag, printString));
        recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    private static class LogViewHolder extends RecyclerView.ViewHolder {

        TextView mTvTag;
        TextView mTvMsg;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTag = itemView.findViewById(R.id.tv_tag);
            mTvMsg = itemView.findViewById(R.id.tv_msg);
        }
    }

    private static class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {

        private LayoutInflater inflater;
        private List<DingLogMo> logs = new ArrayList<>();

        public LogAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        public void addItem(DingLogMo logItem) {
            logs.add(logItem);
            notifyItemInserted(logs.size() - 1);
        }

        @NonNull
        @Override
        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.hilog_item, parent, false);
            return new LogViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            DingLogMo logItem = logs.get(position);
            int color = getHighlightColor(logItem.level);
            holder.mTvTag.setTextColor(color);
            holder.mTvMsg.setTextColor(color);

            holder.mTvTag.setText(logItem.getFlattened());
            holder.mTvMsg.setText(logItem.log);
        }

        @Override
        public int getItemCount() {
            return logs.size();
        }

        // 根据不同的log级别获取不同的颜色
        private int getHighlightColor(int logLevel) {
            int highlight;
            switch (logLevel) {
                case DingLogType.V:
                    highlight = 0xffbbbbbb;
                    break;
                case DingLogType.D:
                    highlight = 0xffffffff;
                    break;
                case DingLogType.I:
                    highlight = 0xff6a8759;
                    break;
                case DingLogType.W:
                    highlight = 0xffbbb529;
                    break;
                case DingLogType.E:
                    highlight = 0xffff6b68;
                    break;
                default:
                    highlight = 0xffffff00;
                    break;
            }
            return highlight;
        }
    }
}
