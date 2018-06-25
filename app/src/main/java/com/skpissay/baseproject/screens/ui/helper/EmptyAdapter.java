package com.skpissay.baseproject.screens.ui.helper;

import android.content.Context;
import android.support.annotation.IntegerRes;

/**
 * Created by skpissay on 25/06/18.
 */

public class EmptyAdapter extends RecyclerAdapter<EmptyViewHolder> {
    private String mTitle;
    private String mText;
    private int mImage;

    public EmptyAdapter(Context context, boolean enabled, String title, String text, @IntegerRes int image) {
        super(context, enabled);
        this.mTitle = title;
        this.mText = text;
        this.mImage = image;
    }

    public void onBindViewHolder(EmptyViewHolder holder, int position) {
        holder.title.setText(this.mTitle);
        holder.text.setText(this.mText);
    }

    public int getItemViewType(int position) {
        return 5000;
    }

    public void changeData(String title, String text, @IntegerRes int image) {
        this.mTitle = title;
        this.mText = text;
        this.mImage = image;
        this.notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.mEnabled?1:0;
    }
}
