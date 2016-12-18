package com.baculsoft.java.android.views.base;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public interface IView {
    void inject();

    void onAttach();

    void onDetach();
}