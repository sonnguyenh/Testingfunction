package com.test.sonnguyenh.testcirclereveal;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.test.sonnguyenh.testcirclereveal.adapter.ListAdapter;
import com.test.sonnguyenh.testcirclereveal.fragment.FragmentDetail;
import com.test.sonnguyenh.testcirclereveal.model.ListData;
import com.test.sonnguyenh.testcirclereveal.model.onAdapterclick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sonnguyenh on 7/22/2016.
 */
public class FragmentSecond extends Fragment implements onAdapterclick {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private OnFragmentTouched listener;
    private ListAdapter mListAdapter;

    public static FragmentSecond newInstance(int centerX, int centerY, int color) {
        Bundle args = new Bundle();
        args.putInt("cx", centerX);
        args.putInt("cy", centerY);
        args.putInt("color", color);
        FragmentSecond fragmentSecond = new FragmentSecond();
        fragmentSecond.setArguments(args);
        return fragmentSecond;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_two, container, false);
        ButterKnife.bind(this, rootView);
        rootView.setBackgroundColor(getArguments().getInt("color"));
        rootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                                       int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                int cx = getArguments().getInt("cx");
                int cy = getArguments().getInt("cy");
                // get the hypothenuse so the radius is from one corner to the other
                int radius = (int) Math.hypot(right, bottom);
                Animator reveal = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, radius);
                reveal.setInterpolator(new DecelerateInterpolator(2f));
                reveal.setDuration(1000);
                reveal.start();
            }
        });

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (listener != null) {
                    listener.onFragmentTouched(FragmentSecond.this, event.getX(), event.getY());
                }
                return true;
            }
        });
        ButterKnife.bind(this, rootView);
        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mListAdapter = new ListAdapter(getContext(), getDummyList(), this);
        recyclerView.setAdapter(mListAdapter);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Animator prepareUnrevealAnimator(float cx, float cy) {
        int radius = getEnclosingCircleRadius(getView(), (int) cx, (int) cy);
        Animator anim = ViewAnimationUtils.createCircularReveal(getView(), (int) cx, (int) cy, radius, 0);
        anim.setInterpolator(new AccelerateInterpolator(2f));
        anim.setDuration(1000);
        return anim;
    }

    /**
     * To be really accurate we have to start the circle on the furthest corner of the view
     *
     * @param v  the view to unreveal
     * @param cx center x of the circle
     * @param cy center y of the circle
     * @return the maximum radius
     */
    private int getEnclosingCircleRadius(View v, int cx, int cy) {
        int realCenterX = cx + v.getLeft();
        int realCenterY = cy + v.getTop();
        int distanceTopLeft = (int) Math.hypot(realCenterX - v.getLeft(), realCenterY - v.getTop());
        int distanceTopRight = (int) Math.hypot(v.getRight() - realCenterX, realCenterY - v.getTop());
        int distanceBottomLeft = (int) Math.hypot(realCenterX - v.getLeft(), v.getBottom() - realCenterY);
        int distanceBottomRight = (int) Math.hypot(v.getRight() - realCenterX, v.getBottom() - realCenterY);

        Integer[] distances = new Integer[]{distanceTopLeft, distanceTopRight, distanceBottomLeft,
                distanceBottomRight};

        return Collections.max(Arrays.asList(distances));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof MainActivity) {
            listener = (OnFragmentTouched) getActivity();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public List<ListData> getDummyList() {
        List<ListData> dummyList = new ArrayList<>();
        dummyList.add(new ListData("Girl 1", "http://imgs.vietnamnet.vn/Images/vnn/2014/04/26/08/20140426082200-tam-a4378.jpg"));
        dummyList.add(new ListData("Girl 2", "http://imgs.vietnamnet.vn/Images/vnn/2014/04/26/08/20140426082200-du-a4378.jpg"));
        dummyList.add(new ListData("Girl 3", "http://imgs.vietnamnet.vn/Images/vnn/2014/04/26/08/20140426082200-anna-a4378.jpg"));
        dummyList.add(new ListData("Girl 4", "http://imgs.vietnamnet.vn/Images/vnn/2014/04/26/08/20140426082200-trinhk-a4378.jpg"));
        dummyList.add(new ListData("Girl 5", "http://imgs.vietnamnet.vn/Images/vnn/2014/04/26/08/20140426082200-tran-a4378.jpg"));
        dummyList.add(new ListData("Girl 6", "http://imgs.vietnamnet.vn/Images/vnn/2014/04/26/08/20140426082200-8-53hg7-1398393718981.jpg"));
        dummyList.add(new ListData("Girl 7", "http://imgs.vietnamnet.vn/Images/vnn/2014/04/26/08/20140426082200-8-53hg7-1398393718981.jpg"));
        dummyList.add(new ListData("Girl 8", "http://imgs.vietnamnet.vn/Images/vnn/2014/04/26/08/20140426082200-tam-a4378.jpg"));
        return dummyList;
    }

    @Override
    public void onItemClick(ListData listData,View v) {
        FragmentDetail fragment = new FragmentDetail();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementReturnTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(R.transition.change_image_trans));
//            setExitTransition(TransitionInflater.from(
//                    getActivity()).inflateTransition(android.R.transition.fade));
            fragment.setSharedElementEnterTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(R.transition.change_image_trans));
//            fragment.setEnterTransition(TransitionInflater.from(
//                    getActivity()).inflateTransition(android.R.transition.fade));
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",listData);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack("fragment_second")
                .addSharedElement(v, getString(R.string.fragment_image_trans))
                .commit();
    }
}
