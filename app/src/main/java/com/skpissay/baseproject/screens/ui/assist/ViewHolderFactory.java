package com.skpissay.baseproject.screens.ui.assist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.skpissay.baseproject.baseclasses.BaseActivity;
import com.skpissay.baseproject.screens.ui.helper.EmptyViewHolder;
import com.skpissay.baseproject.screens.ui.helper.ErrorViewHolder;
import com.skpissay.baseproject.screens.ui.helper.LoadingViewHolder;

/**
 * Created by skpissay on 25/06/18.
 */

public class ViewHolderFactory extends IViewHolderFactory{

    public static final int VHT_CART_ITEM = 4000;
    public static final int VHT_SHORTBOOK_ITEM = 4010;
    public static final int VHT_EMPTY_ITEM = 4020;
    public static final int VHT_LOADING = 4030;
    public static final int VHT_ERROR = 4040;
    public static final int VHT_LOGIN_EMAIL = 4050;
    public static final int VHT_LOGIN_PHONE = 4060;
    public static final int VHT_LOGIN_OTP = 4070;
    public static final int VHT_CART_PRODUCT = 4080;
    public static final int VHT_DIST_CART_ITEM = 4090;
    public static final int VHT_SECTION_HEADER = 4100;
    public static final int VHT_HISTORY_DISTRIBUTOR = 4110;
    public static final int VHT_HISTORY_ITEM = 4120;
    public static final int VHT_DIST_ITEM = 4130;
    public static final int VHT_DIST_SECTION_HEADER = 4140;
    public static final int VHT_DIST_MAPPER_ITEM = 4150;
    public static final int VHT_REGISTER = 4160;
    public static final int VHT_UPLOAD_LICENSE = 4170;
    public static final int VHT_VERIFY_DETAILS = 4180;
    public static final int VHT_PAYMENT_ITEM = 4190;
    public static final int VHT_MAKE_PAYMENT_ITEM = 4200;
    public static final int VHT_NAV_ITEM = 4210;

    private final BaseActivity mContext;
    protected LayoutInflater mInflater;

    public ViewHolderFactory(BaseActivity context) {
        super(context);
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewTypeconfig) {

        switch (viewTypeconfig) {
            case VHT_EMPTY_ITEM:
                return new EmptyViewHolder(mInflater, parent);
            case VHT_LOADING:
                return new LoadingViewHolder(mInflater, parent);
            case VHT_ERROR:
                return new ErrorViewHolder(mInflater, parent);
            default:
                return super.onCreateViewHolder(parent, viewTypeconfig);
        }
    }

    @Override
    public void destroy() {
    }
}
