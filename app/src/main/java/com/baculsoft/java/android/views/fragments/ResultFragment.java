package com.baculsoft.java.android.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baculsoft.java.android.R;
import com.baculsoft.java.android.internal.pojo.TwitterSearch;
import com.baculsoft.java.android.internal.pojo.TwitterSearchResult;
import com.baculsoft.java.android.utils.IConstants;
import com.baculsoft.java.android.views.adapters.ResultAdapter;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
public class ResultFragment extends Fragment {

    @BindView(R.id.rv_result)
    RecyclerView rvResult;

    @BindString(R.string.text_search_result)
    String textSearchResult;

    @BindString(R.string.text_search_count)
    String textSearchCount;

    private View mView;
    private Unbinder mUnbinder;

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_result, container, false);
        mUnbinder = ButterKnife.bind(this, mView);

        return mView;
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        mView = null;
        super.onDestroyView();
    }

    public ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    private void setComponent() {
        final TwitterSearch twitterSearch = getActivity().getIntent().getParcelableExtra(IConstants.IBundles.TWITTER_SEARCH);
        final List<TwitterSearchResult> results = twitterSearch.getResults();
        final int count = twitterSearch.getCount();

        getActionBar().setTitle(textSearchResult);
        getActionBar().setSubtitle(String.format(textSearchCount, count));

        setAdapter(results);
    }

    private void setAdapter(final List<TwitterSearchResult> results) {
        final ResultAdapter adapter = new ResultAdapter(getContext(), results);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        rvResult.setLayoutManager(linearLayoutManager);
        rvResult.smoothScrollToPosition(rvResult.getBottom());
        rvResult.setAdapter(adapter);
    }
}