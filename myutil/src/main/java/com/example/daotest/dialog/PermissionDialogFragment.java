package com.example.daotest.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


import com.example.daotest.R;
import com.example.daotest.base.fragment.BaseDialogFragment;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.download.DownloadTask;


/**
 * @author tian on 2019/9/20
 * 升级提示框
 */
public class PermissionDialogFragment extends BaseDialogFragment {

    private static final String TAG = "BaseDialogFragment";

    private static final String KEY_TITLE = "title";
    private static final String KEY_MSG = "message";
    private static final String KEY_POSITIVE = "positive";
    private static final String KEY_NEGATIVE = "negative";
    private static final String TYPE_CANCELABLE = "cancelable";

    /**
     * 建议升级
     */
    private static final int STRATEGY_OPTIONAL = 1;
    /**
     * 强制升级
     */
    private static final int STRATEGY_FORCE = 2;
    /**
     * 手工升级
     */
    private static final int STRATEGY_MANUAL = 3;

    private TextView btnOk;
    private String positive = "立即更新";
    private static OnDialogClickListener onDialogClickListener;

    public static PermissionDialogFragment newInstance(String title, String msg, String positive, String negative, @CancelableType int cancelableType, OnDialogClickListener onDialogClickListener) {
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        args.putString(KEY_MSG, msg);
        args.putString(KEY_POSITIVE, positive);
        args.putString(KEY_NEGATIVE, negative);
        args.putInt(TYPE_CANCELABLE, cancelableType);
        PermissionDialogFragment fragment = new PermissionDialogFragment();
        fragment.setArguments(args);
        PermissionDialogFragment.onDialogClickListener = onDialogClickListener;
        return fragment;
    }

    public void show(@NonNull Activity activity) {
        if (activity instanceof FragmentActivity) {
            show(((FragmentActivity) activity).getSupportFragmentManager(), PermissionDialogFragment.class.getSimpleName());
        }
    }

    public void show(@NonNull Fragment fragment) {
        show(fragment.getChildFragmentManager(), PermissionDialogFragment.class.getSimpleName());
    }


    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.dialog_permission;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView btnCancel = view.findViewById(R.id.btnNegative);
        btnOk = view.findViewById(R.id.btnPositive);
        TextView titleView = view.findViewById(R.id.titleView);
        TextView msgView = view.findViewById(R.id.msgView);
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String negative = bundle.getString(KEY_NEGATIVE);
            positive = bundle.getString(KEY_POSITIVE);
            String title = bundle.getString(KEY_TITLE);
            String msg = bundle.getString(KEY_MSG);
            int cancelableType = bundle.getInt(TYPE_CANCELABLE, DEFAULT);
            setCancelableType(cancelableType);
            btnCancel.setText(negative);
            titleView.setText(title);
            msgView.setText(msg);
        }

        updateBtn(Beta.getStrategyTask());

    }

    private void updateBtn(DownloadTask strategyTask) {
        int status = strategyTask.getStatus();
        switch (status) {
            case DownloadTask.INIT:
            case DownloadTask.DELETED:
            case DownloadTask.FAILED: {
                btnOk.setText(positive);
            }
            break;
            case DownloadTask.COMPLETE:{
                btnOk.setText("立即安装");
            }
            break;
            case DownloadTask.DOWNLOADING:{

            }
            break;
            case DownloadTask.PAUSED:{
                btnOk.setText("继续下载");
            }
            break;
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btnPositive:
                break;
            case R.id.btnNegative:
                dismiss();
                break;
        }
    }

    public static class Builder{
        private String title;
        private String msg;
        private String negative;
        private String positive;
        private int cancelableType = DEFAULT;
        private OnDialogClickListener onDialogClickListener;

        public Builder(){
        }

        public Builder setTitle(String title){
            this.title =title;
            return this;
        }

        public Builder setMessage(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder setNegativeText(String negative){
            this.negative = negative;
            return this;
        }

        public Builder setPositiveText(String positive){
            this.positive = positive;
            return this;
        }

        public Builder setCancelableType(@CancelableType int cancelableType){
            this.cancelableType = cancelableType;
            return this;
        }

        public Builder setOnDialogClickListener(OnDialogClickListener onDialogClickListener){
            this.onDialogClickListener = onDialogClickListener;
            return this;
        }

        public PermissionDialogFragment build(){
            PermissionDialogFragment dialogFragment = PermissionDialogFragment.newInstance(title, msg, positive, negative, cancelableType, onDialogClickListener);
            return dialogFragment;
        }

    }

    public interface OnDialogClickListener {
        void onPositiveClick();

        void onNegativeClick();
    }
}
