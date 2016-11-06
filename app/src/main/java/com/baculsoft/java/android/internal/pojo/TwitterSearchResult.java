package com.baculsoft.java.android.internal.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
public class TwitterSearchResult implements Parcelable {
    private String mText;
    private String mCreatedAt;

    public static final Creator<TwitterSearchResult> CREATOR = new Creator<TwitterSearchResult>() {
        @Override
        public TwitterSearchResult createFromParcel(Parcel source) {
            return new TwitterSearchResult(source);
        }

        @Override
        public TwitterSearchResult[] newArray(int size) {
            return new TwitterSearchResult[size];
        }
    };

    public TwitterSearchResult() {
    }

    public TwitterSearchResult(final Parcel source) {
        mText = source.readString();
        mCreatedAt = source.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mText);
        dest.writeString(mCreatedAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
    }
}