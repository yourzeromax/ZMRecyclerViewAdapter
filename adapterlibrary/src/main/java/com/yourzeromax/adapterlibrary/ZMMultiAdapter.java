package com.yourzeromax.adapterlibrary;

/* *
 * Created by yourzeromax
 * on 2018/12/3
 *
 *
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class ZMMultiAdapter<T> extends ZMCommonAdapter<T> {


    public ZMMultiAdapter(Context context, List<T> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(viewType, parent, false);
        CommonViewHolder viewHolder = new CommonViewHolder(viewType, view);
        setListener(parent, viewType, view, viewHolder);
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutId(position, mDataList.get(position));
    }

    abstract int getLayoutId(int position, T data);
}
