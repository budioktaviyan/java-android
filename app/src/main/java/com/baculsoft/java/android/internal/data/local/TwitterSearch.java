package com.baculsoft.java.android.internal.data.local;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

import java.util.List;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@Parcel(Parcel.Serialization.BEAN)
public class TwitterSearch {

    @ParcelProperty(value = "twitterSearchResults")
    List<TwitterSearchResult> twitterSearchResults;

    @ParcelProperty(value = "count")
    int count;

    @ParcelConstructor
    public TwitterSearch() {
    }

    public List<TwitterSearchResult> getTwitterSearchResults() {
        return twitterSearchResults;
    }

    public void setTwitterSearchResults(List<TwitterSearchResult> twitterSearchResults) {
        this.twitterSearchResults = twitterSearchResults;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}