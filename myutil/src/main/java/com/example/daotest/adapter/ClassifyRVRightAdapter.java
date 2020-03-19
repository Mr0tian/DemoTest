package com.example.daotest.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daotest.R;
import com.example.daotest.wight.MyChildReycleView;

import java.util.List;

/**
 * @author tian on 2020/1/2
 */
public class ClassifyRVRightAdapter extends RecyclerView.Adapter<ClassifyRVRightAdapter.MyViewHolder> {

    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public ClassifyRVRightAdapter(Context context,List<String> datas){
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_classify_right, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_two_tit.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty() ? 0 : mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_two_tit;
        MyChildReycleView rv_right_child;
        public MyViewHolder(@NonNull View view) {
            super(view);
            txt_two_tit = view.findViewById(R.id.txt_two_tit);//标题
            rv_right_child = view.findViewById(R.id.rv_right_child);
            rv_right_child.setLayoutManager(new LinearLayoutManager(mContext));
            rv_right_child.setAdapter(new ItemRightRecyclerAdapter((AppCompatActivity) mContext));


        }

    }
}
