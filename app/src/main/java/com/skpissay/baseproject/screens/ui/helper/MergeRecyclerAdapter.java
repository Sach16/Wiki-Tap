package com.skpissay.baseproject.screens.ui.helper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.skpissay.baseproject.screens.ui.assist.IDestroyer;
import com.skpissay.baseproject.screens.ui.assist.IViewHolderFactoryListener;
import com.skpissay.baseproject.screens.ui.assist.ViewHolder;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by skpissay on 25/06/18.
 */

public class MergeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IDestroyer, StickyHeaderAdapter<HeaderViewHolder> {
    private static final String TAG = MergeRecyclerAdapter.class.getSimpleName();
    private static final boolean DEBUG = true;
    protected List<RecyclerAdapter> mPieces = new ArrayList();
    protected IViewHolderFactoryListener mViewHolderFactory;
    protected HeaderViewHolderFactory mHeaderViewHolderFactory;
    private HashMap<Integer, CascadeDataSetObserver> mObserverMap = new HashMap();

    public MergeRecyclerAdapter(IViewHolderFactoryListener factory) {
        this.mViewHolderFactory = factory;
    }

    public MergeRecyclerAdapter(IViewHolderFactoryListener factory, HeaderViewHolderFactory headerViewHolderFactory) {
        this.mViewHolderFactory = factory;
        this.mHeaderViewHolderFactory = headerViewHolderFactory;
    }

    public void addAdapter(RecyclerAdapter adapter) {
        this.mPieces.add(adapter);
        this.addDataOberver(adapter);
    }

    public void addAdapterToTop(RecyclerAdapter adapter) {
        this.mPieces.add(0, adapter);
        this.addDataOberver(adapter);
    }

    public void remove(RecyclerAdapter adapter) {
        this.mPieces.remove(adapter);
        this.removeDataOberver(adapter);
    }

    public void removeAll() {
        Iterator var1 = this.mPieces.iterator();

        while(var1.hasNext()) {
            RecyclerView.Adapter piece = (RecyclerView.Adapter)var1.next();
            this.removeDataOberver(piece);
        }

        this.mPieces.clear();
    }

    private void addDataOberver(RecyclerView.Adapter adapter) {
        this.removeDataOberver(adapter);
        MergeRecyclerAdapter.CascadeDataSetObserver observer = new MergeRecyclerAdapter.CascadeDataSetObserver(adapter);
        this.mObserverMap.put(Integer.valueOf(adapter.hashCode()), observer);
        adapter.registerAdapterDataObserver(observer);
    }

    private void removeDataOberver(RecyclerView.Adapter adapter) {
        MergeRecyclerAdapter.CascadeDataSetObserver observer = (MergeRecyclerAdapter.CascadeDataSetObserver)this.mObserverMap.remove(Integer.valueOf(adapter.hashCode()));
        if(observer != null) {
            adapter.unregisterAdapterDataObserver(observer);
        }

    }

    public IViewHolderFactoryListener getViewHolderFactory() {
        return this.mViewHolderFactory;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return this.mViewHolderFactory.onCreateViewHolder(parent, viewType);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder vh, int i) {
        int size;
        for(Iterator var3 = this.mPieces.iterator(); var3.hasNext(); i -= size) {
            RecyclerView.Adapter piece = (RecyclerView.Adapter)var3.next();
            size = piece.getItemCount();
            if(i < size) {
                if(vh instanceof ViewHolder && piece instanceof RecyclerAdapter) {
                    ViewHolder holder = (ViewHolder)vh;
                    if(!holder.isInitialized) {
                        ((RecyclerAdapter)piece).onNewViewHolder(holder);
                        holder.isInitialized = true;
                    }
                }

                piece.onBindViewHolder(vh, i);
                return;
            }
        }

    }

    public int getItemCount() {
        try {
            int e = 0;

            RecyclerView.Adapter piece;
            for(Iterator var2 = this.mPieces.iterator(); var2.hasNext(); e += piece.getItemCount()) {
                piece = (RecyclerView.Adapter)var2.next();
            }

            return e;
        } catch (ConcurrentModificationException var4) {
            return 0;
        }
    }

    public long getItemId(int position) {
        int size;
        for(Iterator var2 = this.mPieces.iterator(); var2.hasNext(); position -= size) {
            RecyclerView.Adapter piece = (RecyclerView.Adapter)var2.next();
            size = piece.getItemCount();
            if(position < size) {
                return piece.getItemId(position);
            }
        }

        return 0L;
    }

    public int getItemViewType(int i) {
        int size;
        for(Iterator var2 = this.mPieces.iterator(); var2.hasNext(); i -= size) {
            RecyclerView.Adapter piece = (RecyclerView.Adapter)var2.next();
            size = piece.getItemCount();
            if(i < size) {
                return piece.getItemViewType(i);
            }
        }

        return -1;
    }

    public void destroy() {
        RecyclerView.Adapter piece;
        for(Iterator var1 = this.mPieces.iterator(); var1.hasNext(); this.removeDataOberver(piece)) {
            piece = (RecyclerView.Adapter)var1.next();
            if(piece instanceof IDestroyer) {
                ((IDestroyer)piece).destroy();
            }
        }

        this.mPieces.clear();
    }

    public List<RecyclerAdapter> getAdapters() {
        return this.mPieces;
    }

    public final void notifyItemRangeChanged(RecyclerView.Adapter adapter, int positionStart, int itemCount) {
        int index = this.mPieces.indexOf(adapter);
        if(index != -1) {
            int offset = 0;

            for(int i = 0; i < index; ++i) {
                offset += ((RecyclerAdapter)this.mPieces.get(i)).getItemCount();
            }

            this.notifyItemRangeChanged(positionStart + offset, itemCount);
        } else {
            this.notifyDataSetChanged();
        }

    }

    public final void notifyItemRangeInserted(RecyclerView.Adapter adapter, int positionStart, int itemCount) {
        int index = this.mPieces.indexOf(adapter);
        if(index != -1) {
            int offset = 0;

            for(int i = 0; i < index; ++i) {
                offset += ((RecyclerAdapter)this.mPieces.get(i)).getItemCount();
            }

            this.notifyItemRangeInserted(positionStart + offset, itemCount);
        } else {
            this.notifyDataSetChanged();
        }

    }

    public final void notifyItemRangeRemoved(RecyclerView.Adapter adapter, int positionStart, int itemCount) {
        int index = this.mPieces.indexOf(adapter);
        if(index != -1) {
            int offset = 0;

            for(int i = 0; i < index; ++i) {
                offset += ((RecyclerAdapter)this.mPieces.get(i)).getItemCount();
            }

            this.notifyItemRangeRemoved(positionStart + offset, itemCount);
        } else {
            this.notifyDataSetChanged();
        }

    }

    public long getHeaderId(int position) {
        int size;
        for(Iterator var2 = this.mPieces.iterator(); var2.hasNext(); position -= size) {
            RecyclerAdapter piece = (RecyclerAdapter)var2.next();
            size = piece.getItemCount();
            if(position < size) {
                return piece.getHeaderId(position);
            }
        }

        return -1L;
    }

    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return new HeaderViewHolder((LayoutInflater)parent.getContext().getSystemService("layout_inflater"), parent);
    }

    public void onBindHeaderViewHolder(HeaderViewHolder vh, int i) {
        int size;
        for(Iterator var3 = this.mPieces.iterator(); var3.hasNext(); i -= size) {
            RecyclerAdapter piece = (RecyclerAdapter)var3.next();
            size = piece.getItemCount();
            if(i < size) {
                piece.onBindHeaderViewHolder(vh, i);
            }
        }

    }

    private class CascadeDataSetObserver extends RecyclerView.AdapterDataObserver {
        RecyclerView.Adapter adapter;

        public CascadeDataSetObserver(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
        }

        public void onChanged() {
            MergeRecyclerAdapter.this.notifyItemRangeChanged(this.adapter, 0, this.adapter.getItemCount());
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            MergeRecyclerAdapter.this.notifyItemRangeChanged(this.adapter, positionStart, itemCount);
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            throw new IllegalAccessError("Not implemented  onItemRangeMoved");
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            MergeRecyclerAdapter.this.notifyItemRangeInserted(this.adapter, positionStart, itemCount);
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            MergeRecyclerAdapter.this.notifyItemRangeRemoved(this.adapter, positionStart, itemCount);
        }
    }
}
