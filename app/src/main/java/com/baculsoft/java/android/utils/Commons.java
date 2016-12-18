package com.baculsoft.java.android.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.ViewGroup;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public final class Commons {
    /**
     * @param view    View container
     * @param message Error message
     * @return Snackbar with LENGTH_LONG
     */
    public Snackbar showGeneralError(final ViewGroup view, final String message) {
        return Snackbar.make(view, message, Snackbar.LENGTH_LONG);
    }

    /**
     * @param context Application context
     * @return ProgressDialog
     */
    public ProgressDialog getProgressDialog(final Context context) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(final DialogInterface dialog, final int keyCode, final KeyEvent event) {
                return true;
            }
        });

        return progressDialog;
    }
}