package com.yourzeromax.adapterlibrary;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/* *
 * Created by yourzeromax
 * on 2018/12/3
 *
 *
 */

public class ZMHeaderFooterAdapter<T> extends RecyclerView.Adapter<ZMCommonAdapter.CommonViewHolder> {

    private List<Integer> mFooterInfo = new ArrayList<>();
    private List<Integer> mHeaderInfo = new ArrayList<>();
    private SparseArray<View> mFooterViews = new SparseArray<>();
    private SparseArray<View> mHeaderViews = new SparseArray<>();

    private ZMCommonAdapter<T> mAdapter;

    public ZMHeaderFooterAdapter(ZMCommonAdapter<T> mAdapter) {
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
        if (mHeaderViews.get(viewType) != null) {
            return new ZMCommonAdapter.CommonViewHolder(viewType, mHeaderViews.get(viewType));
        } else if (mFooterViews.get(viewType) != null) {
            return new ZMCommonAdapter.CommonViewHolder(viewType, mFooterViews.get(viewType));
        }
        return mAdapter.onCreateViewHolder(viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ZMCommonAdapter.CommonViewHolder commonViewHolder, int i) {
        int index;
        if ((index = getIndexInInner(i)) != -1) {
            mAdapter.onBindViewHolder(commonViewHolder, index);
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderViews.size() + mAdapter.getItemCount() + mFooterViews.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mAdapter.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getIndexInInner(position) != -1) {
                        return 1;
                    } else {
                        return gridLayoutManager.getSpanCount();
                    }
                }
            };
            gridLayoutManager.setSpanSizeLookup(spanSizeLookup);
        }
    }

    @Override
    public void onViewAttachedToWindow(ZMCommonAdapter.CommonViewHolder viewHolder) {
        mAdapter.onViewAttachedToWindow(viewHolder);
        int position = viewHolder.getLayoutPosition();
        if (getIndexInHeader(position) != -1 || getIndexInFooter(position) != -1) {
            ViewGroup.LayoutParams lp = viewHolder.itemView.getLayoutParams();
            if (lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    public int getIndexInHeader(int position) {
        if (position >= 0 && position < mHeaderInfo.size()) {
            return position;
        }
        return -1;
    }

    public int getIndexInFooter(int position) {
        if (mFooterViews.size() > 0 && position - mHeaderViews.size() - mAdapter.getItemCount() >= 0) {
            return position - mHeaderViews.size() - mAdapter.getItemCount();
        }
        return -1;
    }

    public int getIndexInInner(int position) {
        if (getIndexInFooter(position) == -1 && getIndexInHeader(position) == -1) {
            return position - mHeaderInfo.size();
        }
        return -1;
    }

    public int getHeaderViewsCount() {
        return mHeaderInfo.size();
    }

    public int getFooterViewsCount() {
        return mFooterInfo.size();
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
