package com.baculsoft.java.android.internal.data.remote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterSearchResponse {

    @JsonProperty(value = "statuses")
    public List<Statuses> statuses;

    @JsonProperty(value = "search_metadata")
    public SearchMetadata searchMetadata;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Statuses {

        @JsonProperty(value = "created_at")
        public String createdAt;

        @JsonProperty(value = "text")
        public String text;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SearchMetadata {

        @JsonProperty(value = "count")
        public int count;
    }
}