package com.baculsoft.java.android.views.base;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public interface IPresenter<T extends IView> {
    void onAttach(T view);

    void onDetach();
}