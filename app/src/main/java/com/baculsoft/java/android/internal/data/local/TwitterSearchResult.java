package com.baculsoft.java.android.internal.data.local;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@Parcel(Parcel.Serialization.BEAN)
public class TwitterSearchResult {

    @ParcelProperty(value = "text")
    String text;

    @ParcelProperty(value = "createdAt")
    String createdAt;

    @ParcelConstructor
    public TwitterSearchResult() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}