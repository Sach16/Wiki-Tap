package com.skpissay.baseproject.screens.ui.helper;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.skpissay.baseproject.R;
import com.skpissay.baseproject.screens.ui.assist.ViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by skpissay on 25/06/18.
 */

public class ErrorViewHolder extends ViewHolder {
    @BindView(R.id.text)
    public TextView text;
    @BindView(R.id.button)
    public Button button;

    public ErrorViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.layout_error, parent, false));
        ButterKnife.bind(this, this.itemView);
    }
}
