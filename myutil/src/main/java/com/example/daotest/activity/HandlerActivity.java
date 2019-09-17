package com.example.daotest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author tian on 2019/9/17
 */
public class HandlerActivity extends AppCompatActivity {

    public final static String UPPER_NUM = "upper";
    @InjectView(R.id.edit)
    EditText edit;
    private EditText etNum;
    private CalThread calThread;



    class CalThread extends Thread {

        private Handler mHandler;

        @Override
        public void run() {
            Looper.prepare();
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x123) {
                        int upper = msg.getData().getInt(UPPER_NUM);
                        List<Integer> nums = new ArrayList<>();
                        //计算从2开始,到upper的所有质数
                        outer:
                        for (int i = 2; i <= upper; i++) {
                            //用i除以从2开始.到i的平方根的所有数
                            int j = 2;
                            while (j <= Math.sqrt(i)) {
                                //如果可以正处,则表明这个数不是质数
                                if (i != 2 && i % j == 0) {
                                    continue outer;
                                }
                                j++;
                            }
                            nums.add(i);
                        }
                        System.out.println(nums.toString());
                    }
                }
            };
            Looper.loop();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        ButterKnife.inject(this);
        calThread = new CalThread();
        //启动新线程
        calThread.start();
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        //创建消息
        Message msg = new Message();
        msg.what = 0x123;
        Bundle bundle = new Bundle();
        bundle.putInt(UPPER_NUM, Integer.parseInt(edit.getText().toString()));
        msg.setData(bundle);
        //向新线程中的Handler发送消息
        calThread.mHandler.sendMessage(msg);
    }


}
