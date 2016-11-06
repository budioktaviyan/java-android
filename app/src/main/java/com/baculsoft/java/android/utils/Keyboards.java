package com.baculsoft.java.android.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
public final class Keyboards {
    private static volatile Keyboards INSTANCE = null;

    public static synchronized Keyboards get() {
        if (INSTANCE == null) {
            INSTANCE = new Keyboards();
        }

        return INSTANCE;
    }

    public void show(final View view, final Context context) {
        final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInputFromInputMethod(view.getWindowToken(), 0);
    }

    public void hide(final View view, final Context context) {
        final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void toggle(final Context context) {
        final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void initKeyboardFocus(final Activity activity, final View view, final int mode) {
        view.requestFocus();
        activity.getWindow().setSoftInputMode(mode);
    }
}