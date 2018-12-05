package com.yourzeromax.adapterlibrary;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/* *
 * Created by yourzeromax
 * on 2018/12/3
 *
 *
 */

public class ZMHeaderFooterAdapter<T> extends RecyclerView.Adapter<ZMCommonAdapter.CommonViewHolder> {
    List<Integer> mFooterInfo;
    List<Integer> mHeaderInfo;
    SparseArray<View> mFooterViews;
    SparseArray<View> mHeaderViews;

    private ZMCommonAdapter<T> mAdapter;

    public ZMHeaderFooterAdapter(ZMCommonAdapter<T> mAdapter) {
        super();
        this.mAdapter = mAdapter;
    }

    @Override
    public int getItemViewType(int position) {
        int index;
        if ((index = getIndexInHeader(position)) != -1) {
            return mHeaderInfo.get(index);
        } else if ((index = getIndexInFooter(position)) != -1) {
            return mFooterInfo.get(index);
        } else if ((index = getIndexInInner(position)) != -1) {
            return mAdapter.getItemViewType(index);
        }
        return -1;
    }

    @NonNull
    @Override
    public ZMCommonAdapter.CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (mFooterViews.get(viewType) != null) {
            return new ZMCommonAdapter.CommonViewHolder(viewType, mFooterViews.get(viewType));
        } else if (mHeaderViews.get(viewType) != null) {
            return new ZMCommonAdapter.CommonViewHolder(viewType, mFooterViews.get(viewType));
        }
        return mAdapter.onCreateViewHolder(viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ZMCommonAdapter.CommonViewHolder commonViewHolder, int i) {
        int index;
        if ((index = getIndexInInner(i)) != -1) {
            mAdapter.onBindViewHolder(commonViewHolder, i);
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderInfo.size() + mAdapter.getItemCount() + mFooterInfo.size();
    }


    public int getIndexInHeader(int position) {
        if (position >= 0 && position < mFooterInfo.size()) {
            return position;
        }
        return -1;
    }

    public int getIndexInFooter(int position) {
        if (position >= mHeaderInfo.size() + mAdapter.getItemCount() && position < getItemCount()) {
            return position - mHeaderInfo.size() - mAdapter.getItemCount();
        }
        return -1;
    }

    public int getIndexInInner(int position) {
        if (getIndexInFooter(position) != -1 && getIndexInHeader(position) != -1) {
            return position - mHeaderInfo.size();
        }
        return -1;
    }

    public void addHeaderView(int layoutId, View view) {
        mHeaderInfo.add(layoutId);
        mHeaderViews.put(layoutId, view);
    }

    public void addFooterView(int layoutId, View view) {
        mFooterInfo.add(layoutId);
        mFooterViews.put(layoutId, view);
    }
}
