package com.example.daotest.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.daotest.R;
import com.example.daotest.dialog.UpdateMoneyDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author tian on 2019/12/25
 * 推荐使用DialogFragment,这样可以随Activity生命周期管理对话框/弹出浮层的生命周期
 */
public class DialogFragmentTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_broad_receiver_test);
        ButterKnife.inject(this);


    }

    @OnClick(R.id.btnClick)
    public void onViewClicked() {
        showPromptDialog("dsafds");
    }

    /**
     * 推荐使用DialogFragment,这样便于管理
     */
    private void showPromptDialog(String text){
        UpdateMoneyDialog dialog = UpdateMoneyDialog.getInstance("ce",12,"ceshi");
        dialog.show(getSupportFragmentManager(),"dialog");
    }

}
