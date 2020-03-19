package com.example.daotest.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.DaoUtil;
import com.example.daotest.R;
import com.example.daotest.bean.UserBean;
import com.example.daotest.dao.UserBeanDao;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author tian on 2019/8/26
 */
public class EditActivity extends AppCompatActivity {


    @InjectView(R.id.user_name)
    EditText userName;
    @InjectView(R.id.user_password)
    EditText userPassword;

    private UserBeanDao userBeanDao;
    private DaoUtil daoUtil = new DaoUtil();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.inject(this);
        userBeanDao = daoUtil.getUserBeanDao();

    }

    @OnClick({R.id.btn_save, R.id.btn_see})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save:

                UserBean userBean = new UserBean();
                Random random = new Random(100000);
                userBean.setId(10086);
                userBean.setName(userName.getText().toString());
                userBean.setPassword(userPassword.getText().toString());
                userBeanDao.insert(userBean);
                Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_see:
                userBeanDao = daoUtil.getUserBeanDao();

                UserBean userBeanGetData = userBeanDao.load((long) 10086);
                if (userBeanGetData != null) {

                    Toast.makeText(getApplicationContext(),"用户名:"+ userBeanGetData.getName()+"  密码:"+ userBeanGetData.getPassword(),Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),"数据库无此数据",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
