package com.skpissay.baseproject.screens.ui.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.skpissay.baseproject.screens.ui.assist.IDestroyer;
import com.skpissay.baseproject.screens.ui.assist.IViewHolderFactory;
import com.skpissay.baseproject.screens.ui.assist.IViewHolderFactoryListener;
import com.skpissay.baseproject.screens.ui.assist.ViewHolder;

/**
 * Created by skpissay on 25/06/18.
 */

public class HeaderViewHolderFactory implements IViewHolderFactoryListener, IDestroyer {
    public static final int HEADER_DATE = 1;
    LayoutInflater mLayoutInflater;

    HeaderViewHolderFactory(Context context) {
        this.mLayoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }

    public void destroy() {
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewTypeconfig) {
        switch(viewTypeconfig) {
            case 1:
                return new HeaderViewHolder(this.mLayoutInflater, parent);
            default:
                return null;
        }
    }
}
