package com.baculsoft.java.android.views.result;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baculsoft.java.android.R;
import com.baculsoft.java.android.internal.data.local.TwitterSearchResult;
import com.baculsoft.java.android.utils.Dates;

import java.util.Date;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultHolder> {
    private final Dates mDates;
    private final List<TwitterSearchResult> mResults;

    public ResultAdapter(final Dates dates, final List<TwitterSearchResult> results) {
        mDates = dates;
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
        final Date date = mDates.getDateTime(mResults.get(position).getCreatedAt());
        final String dateTime = mDates.getDateTime(date);

        holder.getResultText().setText(convertToHtml(text));
        holder.getResultDate().setText(String.format(holder.getTextPosted(), dateTime));
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

        @BindString(R.string.text_posted)
        String textPosted;

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

        public String getTextPosted() {
            return textPosted;
        }
    }
}