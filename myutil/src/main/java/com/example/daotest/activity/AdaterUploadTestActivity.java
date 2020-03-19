package com.example.daotest.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daotest.R;

import com.example.daotest.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author tian on 2019/12/30
 */
public class AdaterUploadTestActivity extends AppCompatActivity {

    @InjectView(R.id.rv_test)
    RecyclerView rvTest;

    @InjectView(R.id.rv_test2)
    RecyclerView rvTest2;

    List<String> mList;

    TestAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_upload);
        ButterKnife.inject(this);

        mList = new ArrayList<>();
        for (int i=0; i<30;i++){
            mList.add("测试" + i+"号");
        }
        mAdapter = new TestAdapter(getApplicationContext(), mList );

        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),3);
        rvTest.setLayoutManager(manager);
        rvTest.setAdapter(mAdapter);

    }
}
