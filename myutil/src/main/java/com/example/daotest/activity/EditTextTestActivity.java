package com.example.daotest.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import tv.danmaku.ijk.media.player.pragma.DebugLog;

/**
 * @author tian on 2019/12/12
 */
public class EditTextTestActivity extends AppCompatActivity {

    @InjectView(R.id.ed_test)
    EditText edTest;
    @InjectView(R.id.btn_click)
    Button btnClick;
    @InjectView(R.id.img_test)
    ImageView imgTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_text_test);
        ButterKnife.inject(this);
    }

    /**
     * 监听控件输入文字之后的状态
     */
    @OnTextChanged(value = R.id.ed_test, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void afterTextChanged(Editable s) {
        Log.e("TAG", "afterTextChanged, s = " + s);

        //正则判断是否是本app的录音文件
        String pattern = "^[Z,z][R,r]\\d{8}$";
        Pattern r = Pattern.compile(pattern);
        if (TextUtils.isEmpty(s)) {
            return;
        }
        Matcher m = r.matcher(s);
        if (m.matches()) {
            Log.e("TAG", "输入信息匹配成功,请求数据");
            edTest.setEnabled(false);
        }
    }

    @OnClick(R.id.btn_click)
    public void onViewClicked() {
        prtSreen();

    }

    /**
     * 截取除了导航栏之外的屏幕
     */
    private void prtSreen() {
        View dView = getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
        if (bitmap != null) {
            try {
                //获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                //图片文件路径
                String filePath = sdCardPath + File.separator + "screenshot.png";
                File file = new File(filePath);
                if (!file.exists()){
                    file.createNewFile();
                }
                FileOutputStream os = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
                os.flush();
                os.close();
                DebugLog.d("a7888", "存储完成");
                Log.i("TAG","存储完成");
                Toast.makeText(this,"截屏成功",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("TAG", "存储失败");

            }


        }

    }

}
