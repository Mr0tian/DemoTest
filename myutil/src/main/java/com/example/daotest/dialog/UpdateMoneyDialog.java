package com.example.daotest.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.daotest.R;

/**
 * @author tian on 2019/12/25
 */
public class UpdateMoneyDialog extends DialogFragment {

    private Activity activity;
    private Context context;
    private LayoutInflater inflater;
    private Dialog dialog;
    private View view;
    private TextView tvCancel;
    private TextView tvConfirm;
    private EditText editText;
    private int position;
    private TextView tvName;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = (Activity) context;
    }

    public static UpdateMoneyDialog getInstance(String money, int position, String name){
        UpdateMoneyDialog imgShowDialog = new UpdateMoneyDialog();
        Bundle bundle = new Bundle();
        bundle.putString("money",money);
        bundle.putInt("pos", position);
        bundle.putString("name",name);
        //传入值,跟Fragment传值方法一样
        imgShowDialog.setArguments(bundle);
        return imgShowDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        inflater = LayoutInflater.from(context);
        dialog = new Dialog(context, R.style.bran_online_supervise_dialog);
        view = inflater.inflate(R.layout.dialog_test,null);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvConfirm =  view.findViewById(R.id.tv_confirm);
        tvName =  view.findViewById(R.id.tv_content);

        //设置取消标题
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置点击外部不可以消失
        dialog.setCanceledOnTouchOutside(true);
        //设置即使点击返回键也不会退出
        setCancelable(true);
        dialog.setContentView(view);

        //将值取出来
        Bundle bundle = getArguments();
        if(null != bundle){
            position = bundle.getInt("pos");
            String name = bundle.getString("name");
            tvName.setText(name);
        }
        tvCancel.setOnClickListener(v->{
            dialog.dismiss();
        });

        //获取当前Activity所在的窗体
        Window dialogWIndow = dialog.getWindow();
        //设置软键盘弹出模式
        dialogWIndow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWIndow.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置Dialog高度自适应
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //将属性赋予给窗体
        dialogWIndow.setAttributes(lp);
        return dialog;


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }


}
