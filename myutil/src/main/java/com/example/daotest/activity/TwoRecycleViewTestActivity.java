package com.example.daotest.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daotest.R;
import com.example.daotest.adapter.ClassifyRVRightAdapter;
import com.example.daotest.adapter.ClassifyRVleftAdapter;
import com.example.daotest.manager.AdvertiseLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tian on 2019/12/31
 */
public class TwoRecycleViewTestActivity extends AppCompatActivity {

    private boolean isScroll = false;

    private RecyclerView rv_left_classify;
    private RecyclerView rv_right_classify;
    private List<String> dataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_recycle);
        rv_left_classify = findViewById(R.id.rv_left);
        rv_right_classify = findViewById(R.id.rv_right);

        initViewAndData();
    }

    /**
     * 初始化数据跟view
     */
    private void initViewAndData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add("数据第" + i + "条");
        }

        //右边recycleView
        //这个是自定义后的layoutManager
        final RecyclerView.LayoutManager layoutManagerRight = new AdvertiseLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_right_classify.setLayoutManager(layoutManagerRight);
        ClassifyRVRightAdapter classifyAdapterRight = new ClassifyRVRightAdapter(this, dataList);
        rv_right_classify.setAdapter(classifyAdapterRight);

        //左边的recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_left_classify.setLayoutManager(layoutManager);
        final ClassifyRVleftAdapter classifyAdapterLeft = new ClassifyRVleftAdapter(this, dataList);
        rv_left_classify.setAdapter(classifyAdapterLeft);

        //右边recyclerview
        //右边recyclerview在滚动的时候监听第一个可见或者最后可见的item，这里是利用LayoutManager才可以监听到的
        rv_right_classify.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                int lastItemPosition = 0;//最后可见 右边
                int firstItemPosition;//第一次可见 右边
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置    这两个随便选一个
                    lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    firstItemPosition = linearManager.findFirstVisibleItemPosition();

                    Log.e("可见item的位置--->>", "最后一个可见==" + lastItemPosition + "第一个可见==" + firstItemPosition);
                }
                //设置左边的RecyclerView的被点击
                classifyAdapterLeft.setSelectedPosition(lastItemPosition);
                //刷新左边的RecyclerView，否则选中无效(亲自踩坑)
                classifyAdapterLeft.notifyDataSetChanged();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //如果是手动滑动右边RecyclerView，则isScroll为true，要刷新左边的RecyclerView，否则为false，不刷新
                //这里说明一下，newState的三种情况
                //RecyclerView停止滚动public static final int SCROLL_STATE_IDLE = 0;//RecyclerView正在被外部拖拽,一般为用户正在用手指滚动public static final int SCROLL_STATE_DRAGGING = 1;//自动滚动开始public static final int SCROLL_STATE_SETTLING = 2;
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    isScroll = true;
                } else {
                    isScroll = false;
                }
            }
        });

        //左边recyclerview
        //点击左边的RecyclerView的某个item，右边RecyclerView相对应指定item
        classifyAdapterLeft.setOnItemClickListener((view, position) -> {
            //右边RecyclerView相对应置顶
            rv_right_classify.smoothScrollToPosition(position);
            //左边的RecyclerView被选中时改变背景颜色
            classifyAdapterLeft.setSelectedPosition(position);
            //刷新左边被选中的item，否则背景颜色不改变无效(亲自踩坑)
            classifyAdapterLeft.notifyDataSetChanged();
        });

    }

}
