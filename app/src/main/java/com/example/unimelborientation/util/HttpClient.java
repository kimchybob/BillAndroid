package com.example.unimelborientation.util;

import android.os.Looper;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

public class HttpClient {
    private static final String BASE_URL = "http://192.168.0.18:8081/";

//            "http://10.0.2.2:8082/"; //Todo

    public static AsyncHttpClient syncHttpClient= new SyncHttpClient();
    public static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    public static AsyncHttpClient myClient = getClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        myClient.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        myClient.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, StringEntity entity, String contentType, AsyncHttpResponseHandler responseHandler) {
        myClient.post(null, getAbsoluteUrl(url), entity, contentType, responseHandler );
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    private static AsyncHttpClient getClient()
    {
        // Return the synchronous HTTP client when the thread is not prepared
        if (Looper.myLooper() == null)
            return syncHttpClient;
        return asyncHttpClient;
    }

    public static void authorization (String token)
    {
        myClient.addHeader("Authorization", token);
    }
}
