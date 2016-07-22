package com.test.sonnguyenh.testcirclereveal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.sonnguyenh.testcirclereveal.R;
import com.test.sonnguyenh.testcirclereveal.model.ListData;
import com.test.sonnguyenh.testcirclereveal.model.onAdapterclick;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sonnguyenh on 7/22/2016.
 */
public class ImageListViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.imgView)
    ImageView imgView;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    Context context;
    private onAdapterclick adapterClick;
    private View itemView;

    public ImageListViewHolder(Context context, View itemView, onAdapterclick onAdapterclick) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
        adapterClick = onAdapterclick;
        this.itemView = itemView;
    }

    public void updateData(final ListData listData) {
        Picasso.with(context).load(listData.getImgUrl()).into(imgView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterClick.onItemClick(listData, imgView);
            }
        });
    }
}
