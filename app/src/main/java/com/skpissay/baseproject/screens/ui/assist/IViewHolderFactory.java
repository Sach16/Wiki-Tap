package com.skpissay.baseproject.screens.ui.assist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.skpissay.baseproject.baseclasses.BaseActivity;
import com.skpissay.baseproject.screens.ui.helper.EmptyViewHolder;
import com.skpissay.baseproject.screens.ui.helper.ErrorViewHolder;
import com.skpissay.baseproject.screens.ui.helper.HeaderViewHolder;
import com.skpissay.baseproject.screens.ui.helper.LoadingViewHolder;

/**
 * Created by skpissay on 25/06/18.
 */

public class IViewHolderFactory implements IViewHolderFactoryListener, IDestroyer {
    public static final int VHT_EMPTY_ITEM = 5000;
    public static final int VHT_LOADING = 5010;
    public static final int VHT_ERROR = 5020;
    public static final int VHT_LOGIN_EMAIL = 4050;
    public static final int VHT_LOGIN_PHONE = 4060;
    public static final int VHT_LOGIN_OTP = 4070;
    public static final int VHT_SALESMAN = 4080;
    public static final int VHT_HEADER = 4090;
    public static final int VHT_ORDER_LIST_ITEM = 3000;
    public static final int RETAILER_LIST_ITEM = 1000;
    public static final int VHT_DRAFT_ITEM = 1001;
    public static final int ORDER_INFO_HEADER = 1002;
    public static final int ORDER_INFO_FOOTER = 1003;
    public static final int ORDER_INFO_ITEM = 1004;
    public static final int VHT_RETAILER_ORDER_ITEM = 1005;
    public static final int VHT_RETAILER_INFO = 1006;
    public static final int VHT_REGISTER = 1007;
    private LayoutInflater mInflater;

    public IViewHolderFactory(BaseActivity activity) {
        this.mInflater = LayoutInflater.from(activity);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewTypeconfig) {
        switch(viewTypeconfig) {
            case 4090:
                return new HeaderViewHolder(this.mInflater, parent);
            case 5000:
                return new EmptyViewHolder(this.mInflater, parent);
            case 5010:
                return new LoadingViewHolder(this.mInflater, parent);
            case 5020:
                return new ErrorViewHolder(this.mInflater, parent);
            default:
                return null;
        }
    }

    public void destroy() {
    }
}
