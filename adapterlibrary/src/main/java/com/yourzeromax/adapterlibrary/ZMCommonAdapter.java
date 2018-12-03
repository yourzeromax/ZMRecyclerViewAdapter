package com.yourzeromax.adapterlibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/* *
 * Created by yourzeromax
 * on 2018/12/3
 *
 *
 */

public abstract class ZMCommonAdapter<T> extends RecyclerView.Adapter<ZMCommonAdapter.CommonViewHolder> {
    protected List<T> mDataList;
    LayoutInflater mInflater;
    private int mLayoutId;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public ZMCommonAdapter(Context context, List<T> dataList, int layoutId) {
        this.mInflater = LayoutInflater.from(context);
        this.mDataList = dataList;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(mLayoutId, parent, false);
        CommonViewHolder vh = new CommonViewHolder(mLayoutId, v);
        setListener(parent, viewType, v, vh);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder commonViewHolder, int position) {
        onBindViewHolder(commonViewHolder, mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setDataList(List<T> dataList) {
        mDataList = dataList;
    }

    public List<T> getDataList() {
        return mDataList;
    }

    protected void setListener(final ViewGroup parent, int viewType, View view, final CommonViewHolder holder) {
        if (mOnItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(parent, v, holder.getAdapterPosition());
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClick(parent, v, holder.getAdapterPosition());
                    return false;
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    public abstract void onBindViewHolder(CommonViewHolder viewHolder, T data);

    class CommonViewHolder extends RecyclerView.ViewHolder {
        private int mLayoutId;
        private View mItemView;
        private SparseArray<View> mViews;

        public CommonViewHolder(int layoutId, View itemView) {
            super(itemView);
            this.mLayoutId = layoutId;
            this.mItemView = itemView;
            this.mViews = new SparseArray<>();
        }

        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mItemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public int getLayoutId() {
            return mLayoutId;
        }

        public View getItemView() {
            return itemView;
        }

        public CommonViewHolder setText(int viewId, String text) {
            TextView tv = getView(viewId);
            tv.setText(text);
            return this;
        }

        public CommonViewHolder setTextColor(int viewId, int color) {
            TextView tv = getView(viewId);
            tv.setTextColor(color);
            return this;
        }

        public CommonViewHolder setImageResource(int viewId, int resId) {
            ImageView view = getView(viewId);
            view.setImageResource(resId);
            return this;
        }

        public CommonViewHolder setEditText(int viewId, String text) {
            EditText et = getView(viewId);
            et.setText(text);
            return this;
        }
        // ...
        // you can add some other methods here...
    }

    /**
     * Interface definition for a callback to be invoked when an item in this
     * RecyclerView has been clicked.
     */
    public interface OnItemClickListener<T> {
        void onItemClick(ViewGroup parent, View view, int position);
    }

    /**
     * Interface definition for a callback to be invoked when an item in this
     * view has been clicked and held.
     */
    public interface OnItemLongClickListener<T> {
        boolean onItemLongClick(ViewGroup parent, View view, int position);
    }
}
