package com.example.daotest.adapter;

import android.os.CountDownTimer;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author tian on 2019/11/14
 * 时间倒计时工具
 */
public class AutoRollAdapter extends RecyclerView.Adapter<AutoRollAdapter.BaseViewHolder> {

    HashMap<String, CountDownTimer>


    class BaseViewHolder extends RecyclerView.ViewHolder{

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
