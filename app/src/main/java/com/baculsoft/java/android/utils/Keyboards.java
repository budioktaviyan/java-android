package com.baculsoft.java.android.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public final class Keyboards {
    /**
     * @param view    View Container
     * @param context Application Context
     */
    public void show(final View view, final Context context) {
        final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInputFromInputMethod(view.getWindowToken(), 0);
    }

    /**
     * @param view    View Container
     * @param context Application Context
     */
    public void hide(final View view, final Context context) {
        final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * @param context Application Context
     */
    public void toggle(final Context context) {
        final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * @param activity Current activity
     * @param view     View container
     * @param mode     Soft input mode
     */
    public void initKeyboardFocus(final Activity activity, final View view, final int mode) {
        view.requestFocus();
        activity.getWindow().setSoftInputMode(mode);
    }
}