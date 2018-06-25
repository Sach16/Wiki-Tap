package com.skpissay.baseproject.screens.ui.helper;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.skpissay.baseproject.R;
import com.skpissay.baseproject.screens.ui.assist.ViewHolder;

/**
 * Created by skpissay on 25/06/18.
 */

public class LoadingViewHolder extends ViewHolder {
    public static final String LOG_TAG = LoadingViewHolder.class.getSimpleName();

    public LoadingViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.layout_loading, parent, false));
    }
}
