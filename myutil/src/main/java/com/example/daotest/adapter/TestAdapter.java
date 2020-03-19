package com.example.daotest.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.daotest.R;
import com.example.daotest.activity.AsyncTaskActivity;
import com.example.daotest.activity.HandlerActivity;
import com.example.daotest.wight.ProgressImageView;
import com.example.daotest.wight.YWaveLoadView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.TreeMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author tian on 2019/12/30
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyHolder> {


    private List<String> mList;
    private Context mContext;

    public TestAdapter(Context mContext, List mList){
        super();
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_upload_test,parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577785136477&di=5e84eeec6e8ffe10d1c252d03f550cf7&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F11385343fbf2b21155022c0ac88065380cd78e42.jpg";

        holder.tvUpload.setText(mList.get(position));


        holder.emptyImageView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                holder.emptyImageView.setVisibility(View.GONE);
                holder.progressImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_mobike));

                Glide.with(mContext)
                        .load(url)
                        .into(holder.progressImageView);
                holder.progressImageView.setVisibility(View.VISIBLE);

                DownTask task = new DownTask(mContext, holder.progressImageView);
                try {
                    task.execute(new URL("http://www.crazyit.org/index.php"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    class DownTask extends AsyncTask<URL,Integer,String>{

        private ProgressImageView progressBar;
        //定义记录已经读取行的数量
        int hasRead = 0;
        Context mContext;
        float progress;
        public DownTask(Context ctx,ProgressImageView progressBar) {
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


        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            Log.i("TAG","数据"+values[0]);

            if (progress >= 1) {
                progress = 1f;
                progressBar.setProgress(progress);
            } else {
                progress += 0.01f;
                progressBar.setProgress(progress);

            }
        }
    }


    @Override
    public int getItemCount() {
        return mList.isEmpty()? 0 : mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        @InjectView(R.id.progress_image_view)
        ProgressImageView progressImageView;

        @InjectView(R.id.iv_empty_img)
        ImageView emptyImageView;

        @InjectView(R.id.tv_upload)
        TextView tvUpload;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

}
