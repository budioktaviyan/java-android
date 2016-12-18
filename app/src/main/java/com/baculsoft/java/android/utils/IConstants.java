package com.baculsoft.java.android.utils;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public interface IConstants {

    interface IKeys {
        String API_KEY = "6d6bef05027caf28017ba74ebda3c6e0";
    }

    interface IUrls {
        String BASE_URL = "http://ibacor.com/api/";

        interface IApis {
            String TWITTER_SEARCH = "twitter-search";
        }
    }

    interface IBundles {
        String TWITTER_SEARCH = "twitter_search";
    }

    interface IPatterns {
        String EEEMMMddHHmmsszzzyyyy = "EEE MMM dd HH:mm:ss zzz yyyy";
        String ddMMyyyyHHmmss = "dd/MM/yyyy HH:mm:ss";
    }
}