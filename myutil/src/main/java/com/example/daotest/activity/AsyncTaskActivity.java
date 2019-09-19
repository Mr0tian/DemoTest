package com.example.daotest.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author tian on 2019/9/17
 */
public class AsyncTaskActivity extends AppCompatActivity {

    @InjectView(R.id.show)
    TextView show;
    @InjectView(R.id.progress)
    ProgressBar progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        DownTask task = new DownTask(this,progress);
        try {
            task.execute(new URL("http://www.crazyit.org/index.php"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    class DownTask extends AsyncTask<URL,Integer,String>{

        private ProgressBar progressBar;
        //定义记录已经读取行的数量
        int hasRead = 0;
        Context mContext;

        public DownTask(Context ctx,ProgressBar progressBar) {
            mContext = ctx;
            this.progressBar = progressBar;
        }

        @Override
        protected String doInBackground(URL... params) {

            StringBuilder sb  = new StringBuilder();
            try {
                URLConnection conn = params[0].openConnection();
                //打开conn连接对应的输入流,并将它包装成BufferedReader
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null ){
                    sb.append(line + "\n");
                    hasRead++;
                    publishProgress(hasRead);
                }
                return sb.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            //返回HTML
            show.setText(result);
            //设置进度条不可见
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onPreExecute() {
            //设置进度条可见
            progressBar.setVisibility(View.VISIBLE);
            //设置进度条的当前值
            progressBar.setProgress(0);
            //设置该进度条的最大进度值
            progressBar.setMax(120);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //更新进度
            show.setText("已经读取了["+ values[0]+"]行!");
            progressBar.setProgress(values[0]);
        }
    }



}
