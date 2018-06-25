package com.skpissay.baseproject.baseclasses;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skpissay.baseproject.R;
import com.skpissay.baseproject.screens.ui.helper.EmptyAdapter;
import com.skpissay.baseproject.screens.ui.helper.ErrorViewHolder;
import com.skpissay.baseproject.screens.ui.helper.IEmptyStateListener;
import com.skpissay.baseproject.screens.ui.assist.ViewHolderFactory;
import com.skpissay.baseproject.screens.ui.helper.MergeRecyclerAdapter;
import com.skpissay.baseproject.screens.ui.helper.SingleItemAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by skpissay on 25/06/18.
 */

public abstract class BaseFragment extends Fragment implements IEmptyStateListener, View.OnClickListener{

    protected BaseActivity m_cObjMainActivity;
    protected UIHandler m_cObjUIHandler;
    protected View m_cObjMainView;
    protected boolean m_cIsActivityAttached;

    @Inject
    public ViewHolderFactory mFactory;
    @Nullable
    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    protected EmptyAdapter mEmptyAdapter;
    protected SingleItemAdapter mLoadingAdapter;
    protected MergeRecyclerAdapter mMergeAdapter;
    protected SingleItemAdapter mErrorAdapter;
    protected String mErrorText;

    protected Toolbar mToolbar;
    protected SingleItemAdapter.IViewBinder<ErrorViewHolder> mErrorBinder = new SingleItemAdapter.IViewBinder<ErrorViewHolder>() {

        @Override
        public void onNewViewHolder(int id, ErrorViewHolder holder) {
            holder.text.setText(getErrorText());
            holder.button.setOnClickListener(getErrorButtonClickListener());
        }

        @Override
        public void onBindViewHolder(int id, ErrorViewHolder holder) {

        }
    };

    @Override
    public void onAttach(Activity pObjActivity) {
        super.onAttach(pObjActivity);
        m_cObjMainActivity = (BaseActivity) getActivity();
        m_cObjUIHandler = new UIHandler();
        m_cIsActivityAttached = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return getActivity().getLayoutInflater().inflate(getLayoutResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initViews(view);

        init();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        m_cIsActivityAttached = false;
    }

    @Override
    public void notifyEmptyStateChange() {
        if (mEmptyAdapter != null)
            mEmptyAdapter.changeData(getEmptyTitleText(), getEmptyDescText(), getEmptyImageResource());
        if (mErrorAdapter != null) {
            mErrorAdapter.notifyItemChanged(0);
        }
    }

    public final class UIHandler extends Handler {
        public void handleMessage(Message pObjMessage) {
            handleUIMessage(pObjMessage);
        }
    }

    public void handleUIhandler(Message pObjMessage) {
        Message lObJMsg = new Message();
        lObJMsg.what = pObjMessage.what;
        lObJMsg.arg1 = pObjMessage.arg1;
        lObJMsg.arg2 = pObjMessage.arg2;
        lObJMsg.obj = pObjMessage.obj;
        m_cObjUIHandler.sendMessage(lObJMsg);
    }

    protected abstract void handleUIMessage(Message pObjMessage);

    protected void closeFragment() {
        int count = this.getFragmentManager().getBackStackEntryCount();
        if(count > 0) {
            this.getFragmentManager().popBackStackImmediate();
        }
    }

    public void setArguments(Bundle bundle) {
        try {
            super.setArguments(bundle);
        } catch (Exception var3) {
            this.updateArgs(bundle);
        }
    }

    @Override
    public void onClick(View view) {}

    protected void updateArgs(Bundle bundle) {}

    protected boolean isAttached() {
        return this.getActivity() != null;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public boolean onBackPressed() {
        return false;
    }

    protected abstract int getLayoutResId();

    protected abstract void initViews(View view);

    protected abstract void init();

    protected abstract String getEmptyTitleText();

    protected abstract String getEmptyDescText();

    protected abstract int getEmptyImageResource();

    protected abstract View.OnClickListener getErrorButtonClickListener();

    protected abstract String getErrorText();


}
