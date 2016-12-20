package com.baculsoft.java.android.views.main;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Spinner;

import com.baculsoft.java.android.App;
import com.baculsoft.java.android.R;
import com.baculsoft.java.android.internal.data.local.TwitterSearch;
import com.baculsoft.java.android.internal.injectors.component.ActivityComponent;
import com.baculsoft.java.android.internal.injectors.component.DaggerActivityComponent;
import com.baculsoft.java.android.internal.injectors.module.ActivityModule;
import com.baculsoft.java.android.utils.Commons;
import com.baculsoft.java.android.utils.Keyboards;
import com.baculsoft.java.android.utils.Navigators;
import com.baculsoft.java.android.views.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class MainActivity extends BaseActivity implements MainView {
    private ActivityComponent mComponent;
    private ProgressDialog mProgressDialog;

    @Inject
    MainPresenter mPresenter;

    @Inject
    Commons mCommonUtils;

    @Inject
    Keyboards mKeyboards;

    @Inject
    Navigators mNavigators;

    @BindView(R.id.cl_main)
    CoordinatorLayout clMain;

    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;

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

    @BindString(R.string.error_failed)
    String errorFailed;

    @BindString(R.string.error_unknown)
    String errorUnknown;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponents(Bundle savedInstanceState) {
        inject();
        onAttach();
        setToolbar();
    }

    @Override
    public void inject() {
        mComponent = DaggerActivityComponent.builder().applicationComponent(App.getComponent())
                                                      .activityModule(new ActivityModule(this))
                                                      .build();
        getComponent().inject(this);
    }

    @Override
    public void onAttach() {
        mPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        mPresenter.onDetach();
    }

    @Override
    public void onShowProgressDialog() {
        mProgressDialog = mCommonUtils.getProgressDialog(this);
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(messageSearch);
            mProgressDialog.show();
        }
    }

    @Override
    public void onDismissProgressDialog() {
        if (this.isFinishing()) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (this.isDestroyed()) {
                return;
            }
        }

        if ((null != mProgressDialog) && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            reset();
        }
    }

    @Override
    public void onShowError() {
        mCommonUtils.showGeneralError(clMain, errorFailed).show();
    }

    @Override
    public void onConnectionError() {
        mCommonUtils.showGeneralError(clMain, errorUnknown).show();
    }

    @Override
    public void onNavigateView(TwitterSearch twitterSearch) {
        mNavigators.openResultActivity(this, twitterSearch);
    }

    @Override
    protected void onDestroy() {
        onDetach();
        super.onDestroy();
    }

    @OnTextChanged(R.id.tiet_main_query)
    public void onTietMainQueryTextChanged() {
        final String query = tietMainQuery.getText().toString();
        final String page = tietMainPage.getText().toString();

        mPresenter.validateSearch(query, page, btnMain);
    }

    @OnTextChanged(R.id.tiet_main_page)
    public void onTietMainPageTextChanged() {
        final String query = tietMainQuery.getText().toString();
        final String page = tietMainPage.getText().toString();

        mPresenter.validateSearch(query, page, btnMain);
    }

    @OnClick(R.id.btn_main)
    public void onBtnMainClick() {
        final String query = tietMainQuery.getText().toString();
        final String page = tietMainPage.getText().toString();
        final String searchType = spMainType.getSelectedItem().toString().toLowerCase();
        final String resultType = spMainResult.getSelectedItem().toString().toLowerCase();

        mPresenter.getTwitterSearch(query, page, searchType, resultType);
    }

    public ActivityComponent getComponent() {
        return mComponent;
    }

    private void setToolbar() {
        toolbarMain.setPadding(0, getStatusBarHeight(), 0, 0);
        toolbarMain.setTitle(appName);
        toolbarMain.setSubtitle(appDesc);
        setSupportActionBar(toolbarMain);
    }

    private void reset() {
        tietMainQuery.getText().clear();
        tietMainPage.getText().clear();
        spMainType.setSelection(0);
        spMainResult.setSelection(0);
        btnMain.setEnabled(false);
        mKeyboards.hide(clMain, this);
        clMain.requestFocus();
    }
}