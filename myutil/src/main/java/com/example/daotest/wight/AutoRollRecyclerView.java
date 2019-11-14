package com.example.daotest.wight;

import android.content.Context;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;

/**
 * @author tian on 2019/11/13
 */
public class AutoRollRecyclerView extends RecyclerView {

    private static final long TIME_AUTO_POLL = 3000;
    AutoPollTask autoPollTask;

    /**表示是否正在自动轮询*/
    private boolean running;

    /**表示是否可以自动轮询*/
    private boolean canRun;

    public AutoRollRecyclerView(@NonNull Context context) {
        super(context);
        autoPollTask = new AutoPollTask(this);
    }

    /**
     * 自动滚动的类
     */
    static class AutoPollTask implements Runnable{

        private int i = 3;
        private final WeakReference<AutoRollRecyclerView> mReference;

        //使用弱引用持有外部类引用->防止内存泄露
        public AutoPollTask(AutoRollRecyclerView reference){
            this.mReference = new WeakReference<AutoRollRecyclerView>(reference);
        }

        @Override
        public void run() {
            AutoRollRecyclerView recyclerView = mReference.get();
            if (recyclerView != null && recyclerView.running && recyclerView.canRun){
                recyclerView.smoothScrollToPosition(i);
                i += 2;
                recyclerView.postDelayed(recyclerView.autoPollTask, TIME_AUTO_POLL);
            }
        }
    }
    /**
     * 开启:如果正在运行,先停止->再开启
     */
    public void start(){
        if (running){
            stop();
        }
        canRun = true;
        running = true;
        postDelayed(autoPollTask, TIME_AUTO_POLL);
    }

    public void stop(){
        running = false;
        removeCallbacks(autoPollTask);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                break;
        }
        return false;
    }
}
