package com.quangnv.moviedb.util.common;

import com.quangnv.moviedb.util.Constants;

/**
 * Created by quangnv on 04/09/2018
 */

public final class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty() || str.trim().length() == 0;
    }

    public static String genUrlImage(String path) {
        return new StringBuilder()
                .append(Constants.BASE_URL_IMAGE)
                .append(path)
                .toString();
    }
}
