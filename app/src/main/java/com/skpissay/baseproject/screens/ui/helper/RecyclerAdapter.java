package com.skpissay.baseproject.screens.ui.helper;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skpissay.baseproject.screens.ui.assist.IDestroyer;
import com.skpissay.baseproject.screens.ui.assist.IViewHolderFactory;
import com.skpissay.baseproject.screens.ui.assist.ViewHolder;

/**
 * Created by skpissay on 25/06/18.
 */

public abstract class RecyclerAdapter <T extends ViewHolder> extends RecyclerView.Adapter<T> implements IEnabler, IDestroyer {
    private static final String TAG = RecyclerAdapter.class.getSimpleName();
    private static final boolean DEBUG = true;
    protected boolean mEnabled = false;
    protected Context mContext;
    private IViewHolderFactory mFactory;

    public RecyclerAdapter(Context context, boolean enabled) {
        this.mContext = context;
        this.mEnabled = enabled;
    }

    public long getHeaderId(int position) {
        return -1L;
    }

    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    public void onBindHeaderViewHolder(HeaderViewHolder viewholder, int position) {
    }

    public void setFactory(IViewHolderFactory factory) {
        this.mFactory = factory;
    }

    public void destroy() {
    }

    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return (T) this.mFactory.onCreateViewHolder(parent, viewType);
    }

    public void onNewViewHolder(T holder) {
    }

    void setSpannableText(TextView view, String text1, String text2, int color1, int color2) {
        SpannableString text = new SpannableString(text1 + text2);
        text.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, color1)), 0, text1.length(), 33);
        text.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, color2)), text1.length(), text.length(), 33);
        view.setText(text);
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public void setEnabled(boolean enable) {
        if(enable != this.mEnabled) {
            int effectiveCnt = -1;
            if(!enable) {
                effectiveCnt = this.getItemCount();
            }

            this.mEnabled = enable;
            if(enable) {
                effectiveCnt = this.getItemCount();
            }

            if(effectiveCnt != -1) {
                if(enable) {
                    this.notifyItemRangeInserted(0, effectiveCnt);
                } else {
                    this.notifyItemRangeRemoved(0, effectiveCnt);
                }
            } else {
                this.notifyDataSetChanged();
            }
        }

    }
}
