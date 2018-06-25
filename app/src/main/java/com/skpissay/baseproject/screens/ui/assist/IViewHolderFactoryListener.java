package com.skpissay.baseproject.screens.ui.assist;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by skpissay on 25/06/18.
 */

public interface IViewHolderFactoryListener {
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup var1, int var2);
}
