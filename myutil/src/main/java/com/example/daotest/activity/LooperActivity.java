package com.example.daotest.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.daotest.R;
import com.example.daotest.adapter.AutoRollAdapter;
import com.example.daotest.bean.GroupBookingEntity;
import com.example.daotest.wight.AutoRollRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tian on 2019/11/15
 */
public class LooperActivity extends AppCompatActivity {

    private AutoRollRecyclerView mRecyclerView;
    private List<GroupBookingEntity> list = new ArrayList<>();
    private AutoRollAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper_rv);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            GroupBookingEntity entity = new GroupBookingEntity();
            entity.nickname = "我是一个游客"+i;
            entity.collage_people = "10";
            entity.now_people = i+"";
            entity.end_time = "86400";
            entity.current_time =(100+i*30)+"";
            entity.id="id"+i;
            list.add(entity);
        }
    }

    private void initView() {
        mRecyclerView = (AutoRollRecyclerView) findViewById(R.id.rv_recycleView);
        mAdapter = new AutoRollAdapter(list);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mRecyclerView) {
            mRecyclerView.stop();
        }
        if(mAdapter!=null){
            mAdapter.onDestroy();
        }
    }
}
