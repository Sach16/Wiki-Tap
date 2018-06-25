package com.skpissay.baseproject.screens.ui.helper;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skpissay.baseproject.R;
import com.skpissay.baseproject.screens.ui.assist.ViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by skpissay on 25/06/18.
 */

public class EmptyViewHolder extends ViewHolder {
    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.text)
    public TextView text;
    @BindView(R.id.image)
    public ImageView image;

    public EmptyViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.adapter_empty, parent, false));
        ButterKnife.bind(this, this.itemView);
    }
}
