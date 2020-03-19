package com.example.daotest.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daotest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * Created by ${chenyuexueer}
 * 时间： 2018/9/11.

 * ================================================
 */
public class ItemRightRecyclerAdapter extends RecyclerView.Adapter<ItemRightRecyclerAdapter.ViewHolder> { //包名也需要自动生成,同时注意List传参为泛型
    private AppCompatActivity mActivity;
    private List<Integer> mailList;//数据集合


    public ItemRightRecyclerAdapter(AppCompatActivity mActivity) {
        this.mActivity = mActivity;
        mailList=new ArrayList<>();
        mailList.add(1);
        mailList.add(2);
        mailList.add(3);
        mailList.add(4);
        mailList.add(5);
        mailList.add(6);
        mailList.add(7);
        mailList.add(8);


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int itemViewId = R.layout.sub_item_recycler_right;
        ViewHolder holder = new ViewHolder(LayoutInflater.from(mActivity).inflate(itemViewId, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return null != mailList && mailList.size() > 0 ? mailList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

}