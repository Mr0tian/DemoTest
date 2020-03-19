package com.example.daotest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daotest.R;

import java.util.List;

/**
 * @author tian on 2020/1/2
 */
public class ClassifyRVleftAdapter extends RecyclerView.Adapter<ClassifyRVleftAdapter.MyViewHolder> implements View.OnClickListener {

    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    private int selectedPosition = 0;

    private OnItemClickListener mOnItemClickListener = null;
    private View viewClassify;


    //define interface
    public static interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public ClassifyRVleftAdapter(Context context, List<String> datas){
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_classify_rv_adapter,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv.setText(mDatas.get(position));
        //选中和没选中时,设置不同的颜色
        if (position == selectedPosition){
            holder.tv.setBackgroundColor(Color.GRAY);
        }else {
            holder.tv.setBackgroundColor(Color.WHITE);
        }
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty() ? 0 : mDatas.size();
    }

    @Override
    public void onClick(View view) {
        if(mOnItemClickListener != null){
            //注意智力使用的getTag方法获取position
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public void setSelectedPosition(int selectedPosition){
        this.selectedPosition = selectedPosition;
    }

    public int getSelectedPosition(){
        return selectedPosition;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_classsify);
        }
    }

}
