package com.baculsoft.java.android.views.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.baculsoft.java.android.R;
import com.baculsoft.java.android.internal.api.response.TwitterSearchResponse;
import com.baculsoft.java.android.internal.pojo.TwitterSearch;
import com.baculsoft.java.android.internal.pojo.TwitterSearchResult;
import com.baculsoft.java.android.utils.Connections;
import com.baculsoft.java.android.utils.IConstants;
import com.baculsoft.java.android.utils.Keyboards;
import com.baculsoft.java.android.utils.Navigators;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.baculsoft.java.android.R.id.rl_main;

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
public class MainFragment extends Fragment {

    @BindView(rl_main)
    RelativeLayout rlMain;

    @BindView(R.id.tiet_main_query)
    TextInputEditText tietMainQuery;

    @BindView(R.id.tiet_main_page)
    TextInputEditText tietMainPage;

    @BindView(R.id.sp_main_type)
    Spinner spMainType;

    @BindView(R.id.sp_main_result)
    Spinner spMainResult;

    @BindView(R.id.btn_main)
    Button btnMain;

    @BindString(R.string.app_name)
    String appName;

    @BindString(R.string.app_desc)
    String appDesc;

    @BindString(R.string.message_search)
    String messageSearch;

    private View mView;
    private Unbinder mUnbinder;
    private ProgressDialog mProgressDialog;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        mUnbinder = ButterKnife.bind(this, mView);

        return mView;
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        mView = null;
        super.onDestroyView();
    }

    @OnTextChanged(R.id.tiet_main_query)
    public void onTietMainQueryTextChanged() {
        onValidate();
    }

    @OnTextChanged(R.id.tiet_main_page)
    public void onTietMainPageTextChanged() {
        onValidate();
    }

    @OnEditorAction(R.id.tiet_main_page)
    public boolean onTietMainPageEditorAction() {
        if (btnMain.isEnabled()) {
            onButtonClick();
        }

        return true;
    }

    @OnClick(R.id.btn_main)
    public void onBtnMainClick() {
        onButtonClick();
    }

    public ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    public ProgressDialog getProgressDialog(final Context context) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(messageSearch);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return true;
            }
        });

        return progressDialog;
    }

    private void setComponent() {
        getActionBar().setTitle(appName);
        getActionBar().setSubtitle(appDesc);
        mProgressDialog = getProgressDialog(getContext());
    }

    private void onValidate() {
        if (!TextUtils.isEmpty(tietMainQuery.getText().toString()) &&
            !TextUtils.isEmpty(tietMainPage.getText().toString())) {
            btnMain.setEnabled(true);
        } else {
            btnMain.setEnabled(false);
        }
    }

    private void onButtonClick() {
        Keyboards.get().hide(rlMain, getContext());
        mProgressDialog.show();
        getTwitterSearch();
    }

    private void getTwitterSearch() {
        final String query = tietMainQuery.getText().toString();
        final String typeSearch = spMainType.getSelectedItem().toString().toLowerCase();
        final String resultType = spMainResult.getSelectedItem().toString().toLowerCase();
        final int maxId = Integer.parseInt(tietMainPage.getText().toString());
        final String key = IConstants.IKeys.API_KEY;

        Connections.get().getApi().getTwitterSearch(query, typeSearch, resultType, maxId, key).enqueue(new Callback<TwitterSearchResponse>() {
            @Override
            public void onResponse(Call<TwitterSearchResponse> call, Response<TwitterSearchResponse> response) {
                switch (response.code()) {
                    case 200: {
                        onResetField();

                        if (null != response.body()) {
                            onSearchResult(response.body());
                        } else {
                            Snackbar.make(rlMain, "onSearchResult -> ".concat(String.valueOf(response.body())), Snackbar.LENGTH_SHORT).show();
                            Log.e(MainFragment.class.getSimpleName(), "onSearchResult -> ".concat(String.valueOf(response.body())));
                        }
                        break;
                    }
                    default: {
                        onResetField();
                        Snackbar.make(rlMain, "onUnknownResponse -> ".concat(String.valueOf(response.code())), Snackbar.LENGTH_SHORT).show();
                        Log.e(MainFragment.class.getSimpleName(), "onUnknownResponse -> ".concat(String.valueOf(response.code())));
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<TwitterSearchResponse> call, Throwable throwable) {
                onResetField();
                Snackbar.make(rlMain, "onFailure -> ".concat(throwable.getMessage()), Snackbar.LENGTH_SHORT).show();
                Log.e(MainFragment.class.getSimpleName(), "onFailure -> ".concat(throwable.getMessage()));
            }
        });
    }

    private void onResetField() {
        mProgressDialog.dismiss();
        tietMainQuery.getText().clear();
        tietMainPage.getText().clear();
        spMainType.setSelection(0);
        spMainResult.setSelection(0);
    }

    private void onSearchResult(final TwitterSearchResponse response) {
        final List<TwitterSearchResponse.Statuses> statuses = response.statuses;
        final TwitterSearchResponse.SearchMetadata searchMetadata = response.searchMetadata;

        if (statuses.size() != 0) {
            final List<TwitterSearchResult> results = getTwitterSearchResult(statuses);
            final int count = searchMetadata.count;
            final TwitterSearch twitterSearch = new TwitterSearch();
            twitterSearch.setResults(results);
            twitterSearch.setCount(count);

            Navigators.get().openResultActivity(getContext(), twitterSearch);
        } else {
            Snackbar.make(rlMain, "onSearchResult -> ".concat(String.valueOf(statuses.size())), Snackbar.LENGTH_SHORT).show();
            Log.e(MainFragment.class.getSimpleName(), "onSearchResult -> ".concat(String.valueOf(statuses.size())));
        }
    }

    private List<TwitterSearchResult> getTwitterSearchResult(final List<TwitterSearchResponse.Statuses> statuses) {
        final List<TwitterSearchResult> results = new ArrayList<>();

        for (TwitterSearchResponse.Statuses status : statuses) {
            TwitterSearchResult data = new TwitterSearchResult();
            data.setText(status.text);
            data.setCreatedAt(status.createdAt);
            results.add(data);
        }

        return results;
    }
}