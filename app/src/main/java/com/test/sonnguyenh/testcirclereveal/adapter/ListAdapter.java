package com.test.sonnguyenh.testcirclereveal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.sonnguyenh.testcirclereveal.R;
import com.test.sonnguyenh.testcirclereveal.model.ListData;
import com.test.sonnguyenh.testcirclereveal.model.onAdapterclick;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sonnguyenh on 7/22/2016.
 */
public class ListAdapter extends RecyclerView.Adapter<ImageListViewHolder> {
    private List<ListData> mList;
    private Context context;
    private onAdapterclick mListener;

    public ListAdapter(Context context, List<ListData> mList, onAdapterclick onAdapterclick) {
        this.mList = mList;
        this.context = context;
        mListener = onAdapterclick;
    }

    @Override
    public ImageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageListViewHolder(context, LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false),mListener);
    }

    @Override
    public void onBindViewHolder(ImageListViewHolder holder, int position) {
        holder.updateData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

}
