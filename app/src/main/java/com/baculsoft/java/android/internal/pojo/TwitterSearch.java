package com.baculsoft.java.android.internal.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
public class TwitterSearch implements Parcelable {
    private List<TwitterSearchResult> mResults;
    private int mCount;

    public static final Creator<TwitterSearch> CREATOR = new Creator<TwitterSearch>() {
        @Override
        public TwitterSearch createFromParcel(Parcel source) {
            return new TwitterSearch(source);
        }

        @Override
        public TwitterSearch[] newArray(int size) {
            return new TwitterSearch[size];
        }
    };

    public TwitterSearch() {
    }

    public TwitterSearch(final Parcel source) {
        mResults = new ArrayList<>();
        source.readTypedList(mResults, TwitterSearchResult.CREATOR);

        mCount = source.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mResults);
        dest.writeInt(mCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public List<TwitterSearchResult> getResults() {
        return mResults;
    }

    public void setResults(List<TwitterSearchResult> mResults) {
        this.mResults = mResults;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
    }
}