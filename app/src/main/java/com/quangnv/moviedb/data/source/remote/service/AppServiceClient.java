package com.quangnv.moviedb.data.source.remote.service;

import android.content.Context;
import android.support.annotation.NonNull;

import com.quangnv.moviedb.util.Constants;

/**
 * Created by quangnv on 03/09/2018
 */

public class AppServiceClient extends ServiceClient {

    private static NameApi sInstance;

    public static NameApi getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = createService(context, Constants.END_POINT_URL, NameApi.class);
        }
        return sInstance;
    }
}
