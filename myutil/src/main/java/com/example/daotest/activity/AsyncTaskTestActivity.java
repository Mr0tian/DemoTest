package com.example.daotest.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;
import com.example.daotest.bean.Voice;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author tian on 2019/12/27
 */
public class AsyncTaskTestActivity extends AppCompatActivity {
    @InjectView(R.id.tvInfo)
    TextView tvInfo;
    @InjectView(R.id.img)
    ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_receiver_test);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btnClick)
    public void onViewClicked() {
        doAsyncTask(getApplicationContext());
    }

    public void doBack(){

    }

    public void doAsyncTask(Context context){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {



                return "后台执行完了";
            }

            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

  //  class doAsynTask extends AsyncTask<>

}
