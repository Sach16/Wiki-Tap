package com.skpissay.baseproject.screens.ui.helper;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skpissay.baseproject.R;
import com.skpissay.baseproject.models.Page;
import com.skpissay.baseproject.screens.ui.interfaces.RecyclerUsersListener;
import com.skpissay.baseproject.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.Optional;

/**
 * Created by skpissay on 25/06/18.
 */

public class UsersAdapter extends RecyclerView.Adapter{

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static RecyclerUsersListener mClickListener;
    private static List<Page> mObjJsonPages;
    private Context mObjContext;

    public UsersAdapter(Context pContext) {
        this.mObjContext = pContext;
    }

    public void changeData(List<Page> pPages) {
        mObjJsonPages = pPages;
        notifyDataSetChanged();
    }

    public void setItemClickListener(RecyclerUsersListener listener) {
        mClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mObjJsonPages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mObjJsonPages.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View lView;
        // paging logic
        if (viewType == VIEW_ITEM) {
            lView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_cell, parent, false);
            DataObjectHolder ldataObjectHolder = new DataObjectHolder(lView);
            vh = ldataObjectHolder;
        } else {
            lView = LayoutInflater.from(parent.getContext()).inflate(R.layout.progressdialog_paging, parent, false);
            ProgressViewHolder lprogressViewHolder = new ProgressViewHolder(lView);
            vh = lprogressViewHolder;
        }
        return vh;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Nullable
        @BindView(R.id.user_cell_rl)
        RelativeLayout cvUserCell;

        @Nullable
        @BindView(R.id.image)
        ImageView civUser;

        @Nullable
        @BindView(R.id.title)
        TextView fullNametxt;

        @Nullable
        @BindView(R.id.text)
        TextView titleDesctxt;

        public DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Optional
        @OnClick({R.id.user_cell_ll})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.user_cell_ll:
                    mClickListener.onInfoClick(getPosition(), mObjJsonPages.get(getPosition()), v);
                    break;
            }
        }

        @Optional
        @OnLongClick({R.id.user_cell_ll})
        public boolean onLongClick(View view) {
            switch (view.getId()) {
                case R.id.user_cell_ll:
                    mClickListener.onInfoLongClick(getPosition(), mObjJsonPages.get(getPosition()), view);
                    break;
            }
            return false;
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.progressBar1)
        ProgressBar progressBar;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof DataObjectHolder) {
            try {
                Picasso.with(mObjContext)
                        .load(mObjJsonPages.get(position).getThumbnail().getSource())
                        .error(R.mipmap.ic_launcher_round)
                        .placeholder(R.mipmap.ic_launcher_round)
                        .transform(new CircleTransform())
                        .fit()
                        .into(((DataObjectHolder) holder).civUser);
            } catch (Exception e) {
                ((DataObjectHolder) holder).civUser.setImageResource(R.mipmap.ic_launcher_round);
                e.printStackTrace();
            }

            try {
                ((DataObjectHolder) holder).fullNametxt.setText(mObjJsonPages.get(position).getTitle());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                ((DataObjectHolder) holder).titleDesctxt.setText(mObjJsonPages.get(position).getTerms().getDescription().get(0));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

}
