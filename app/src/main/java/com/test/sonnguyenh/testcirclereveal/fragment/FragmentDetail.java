package com.test.sonnguyenh.testcirclereveal.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.sonnguyenh.testcirclereveal.R;
import com.test.sonnguyenh.testcirclereveal.model.ListData;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sonnguyenh on 7/22/2016.
 */
public class FragmentDetail extends Fragment {
    @Bind(R.id.imgViewTarget)
    ImageView imgView;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvContent)
    TextView tvContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, rootView);
        initArgument();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initArgument(){
        ListData listData = (ListData) getArguments().get("data");
        Picasso.with(getContext()).load(listData.getImgUrl()).into(imgView);
    }
}
