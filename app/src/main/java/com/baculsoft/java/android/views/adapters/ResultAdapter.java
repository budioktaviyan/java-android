package com.baculsoft.java.android.views.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baculsoft.java.android.R;
import com.baculsoft.java.android.internal.pojo.TwitterSearchResult;
import com.baculsoft.java.android.utils.Dates;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultHolder> {
    private final List<TwitterSearchResult> mResults;

    private Context mContext;

    public ResultAdapter(final Context context, final List<TwitterSearchResult> results) {
        mContext = context;
        mResults = results;
    }

    @Override
    public ResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        return new ResultHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultHolder holder, int position) {
        final String text = mResults.get(position).getText();
        final Date date = Dates.get().getDateTime(mResults.get(position).getCreatedAt());
        final String dateTime = Dates.get().getDateTime(date);

        holder.getResultText().setText(convertToHtml(text));
        holder.getResultDate().setText(String.format(mContext.getResources().getString(R.string.text_posted), dateTime));
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    private CharSequence convertToHtml(final String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    class ResultHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_result_text)
        TextView tvResultText;

        @BindView(R.id.tv_result_date)
        TextView tvResultDate;

        public ResultHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public TextView getResultText() {
            return tvResultText;
        }

        public TextView getResultDate() {
            return tvResultDate;
        }
    }
}