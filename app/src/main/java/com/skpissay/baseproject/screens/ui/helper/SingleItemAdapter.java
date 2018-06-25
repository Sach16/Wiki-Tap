package com.skpissay.baseproject.screens.ui.helper;

import android.content.Context;
import android.view.View;

import com.skpissay.baseproject.screens.ui.assist.ViewHolder;

/**
 * Created by skpissay on 25/06/18.
 */

public class SingleItemAdapter extends RecyclerAdapter<ViewHolder> {
    private final int mViewType;
    private final int mId;
    private SingleItemAdapter.IViewBinder mBinder;
    private android.support.v7.widget.RecyclerView.ViewHolder mVH;

    public SingleItemAdapter(Context context, boolean enabled, int id, int viewType) {
        super(context, enabled);
        this.mId = id;
        this.mViewType = viewType;
    }

    public SingleItemAdapter setViewBinder(SingleItemAdapter.IViewBinder binder) {
        this.mBinder = binder;
        return this;
    }

    public int getItemViewType(int position) {
        return this.mViewType;
    }

    public int getItemCount() {
        return this.mEnabled?1:0;
    }

    public View getView() {
        return this.mVH == null?null:this.mVH.itemView;
    }

    public android.support.v7.widget.RecyclerView.ViewHolder getViewHolder() {
        return this.mVH;
    }

    public void onNewViewHolder(ViewHolder holder) {
        this.mVH = holder;
        if(this.mBinder != null) {
            this.mBinder.onNewViewHolder(this.mId, holder);
        }

    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        this.mVH = holder;
        if(this.mBinder != null) {
            this.mBinder.onBindViewHolder(this.mId, holder);
        }

    }

    public interface IViewBinder<V extends android.support.v7.widget.RecyclerView.ViewHolder> {
        void onNewViewHolder(int var1, V var2);

        void onBindViewHolder(int var1, V var2);
    }
}
