package com.example.daotest.base.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.CallSuper;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.DialogFragment;


import com.example.daotest.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author tian on 2019/9/27
 */
public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener, DialogInterface.OnKeyListener {
    public static final int DEFAULT = 0;
    public static final int IS_CANCELED_ON_TOUCH_OUTSIDE = 1;
    public static final int IS_CANCELED_ON_KEYCODE_BACK = 2;
    public static final int NO_CANCELED = 3;
    private int mCancelableType = DEFAULT;
    protected View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, getDialogStyle());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setOnKeyListener(this);
        rootView = inflater.inflate(R.layout.dialog_base, container, false);
        FrameLayout root = (FrameLayout) rootView.findViewById(R.id.dialog_container);
        root.setOnClickListener(this);
        View.inflate(getContext(), getLayoutId(), root);
        init(savedInstanceState);
        return rootView;
    }

    /**
     * dialog样式
     *
     * @return
     */
    protected @StyleRes
    int getDialogStyle() {
        return R.style.Base_Dialog_Style;
    }

    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);

    /**
     * @param cancelableType DEFAULT: 即可以点击dialog外部区域隐藏dialog，也可以点击返回键隐藏dialog
     *                       IS_CANCELED_ON_TOUCH_OUTSIDE: 只可以点击dialog外部区域隐藏dialog
     *                       IS_CANCELED_ON_KEYCODE_BACK: 只可以点击返回键隐藏dialog
     *                       NO_CANCELED: 无论点击dialog外部区域，还是点击返回键都不允许隐藏dialog
     */
    public void setCancelableType(@CancelableType int cancelableType) {
        mCancelableType = cancelableType;
    }

    @CallSuper
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.dialog_container) {
            if (mCancelableType == DEFAULT || mCancelableType == IS_CANCELED_ON_TOUCH_OUTSIDE) {
                dismiss();
            }
        }
    }


    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mCancelableType == IS_CANCELED_ON_TOUCH_OUTSIDE || mCancelableType == NO_CANCELED) {
                return true;
            }
        }
        return false;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DEFAULT, IS_CANCELED_ON_TOUCH_OUTSIDE, IS_CANCELED_ON_KEYCODE_BACK, NO_CANCELED})
    public @interface CancelableType {
    }
}