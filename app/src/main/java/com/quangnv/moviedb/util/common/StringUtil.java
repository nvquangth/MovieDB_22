package com.quangnv.moviedb.util.common;

import com.quangnv.moviedb.util.Constant;

/**
 * Created by quangnv on 04/09/2018
 */

public final class StringUtil {

    public static String genUrlImage(String path) {
        return new StringBuilder()
                .append(Constant.BASE_URL_IMAGE)
                .append(path)
                .toString();
    }
}
