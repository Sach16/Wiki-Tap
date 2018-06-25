package com.skpissay.baseproject.screens.ui.helper;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skpissay.baseproject.R;
import com.skpissay.baseproject.screens.ui.assist.ViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by skpissay on 25/06/18.
 */

public class HeaderViewHolder extends ViewHolder {
    @BindView(R.id.title)
    public TextView title;

    public HeaderViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.adapter_header_item, parent, false));
        ButterKnife.bind(this, this.itemView);
    }
}
