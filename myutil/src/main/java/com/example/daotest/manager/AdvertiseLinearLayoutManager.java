package com.example.daotest.manager;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 用于两级列表联动的自定义linearLayoutManager
 * @author tian on 2020/1/2
 */
public class AdvertiseLinearLayoutManager extends LinearLayoutManager {

    public AdvertiseLinearLayoutManager(Context context) {
        super(context);
    }
    public AdvertiseLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public AdvertiseLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        AdvertiseLinearSmoothScroller linearSmoothScroller = new AdvertiseLinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }
}
