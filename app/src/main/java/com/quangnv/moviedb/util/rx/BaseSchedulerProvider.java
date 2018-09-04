package com.quangnv.moviedb.util.rx;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * Created by quangnv on 04/09/2018
 */

public interface BaseSchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
