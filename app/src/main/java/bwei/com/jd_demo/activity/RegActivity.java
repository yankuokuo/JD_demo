package bwei.com.jd_demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.mvp.my.model.bean.LoginBean;
import bwei.com.jd_demo.mvp.my.model.bean.NickBean;
import bwei.com.jd_demo.mvp.my.model.bean.OrdersBean;
import bwei.com.jd_demo.mvp.my.model.bean.RegBean;
import bwei.com.jd_demo.mvp.my.model.bean.UpOrderBean;
import bwei.com.jd_demo.mvp.my.model.bean.UploadBean;
import bwei.com.jd_demo.mvp.my.model.bean.UserInfoBean;
import bwei.com.jd_demo.mvp.my.persenter.MyPersenter;
import bwei.com.jd_demo.mvp.my.view.MyView;

public class RegActivity extends AppCompatActivity implements MyView{

    @BindView(R.id.reg_name)
    EditText regName;
    @BindView(R.id.reg_pass)
    EditText regPass;
    @BindView(R.id.reg_login)
    Button regLogin;
    private MyPersenter myPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);

        myPersenter = new MyPersenter(this);

    }

    @OnClick({R.id.reg_name, R.id.reg_pass, R.id.reg_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reg_name:
                break;
            case R.id.reg_pass:
                break;
            case R.id.reg_login:
                String name = regName.getText().toString();
                String pass = regPass.getText().toString();
                myPersenter.RegLogin(name,pass);
                finish();
                break;
        }
    }

    @Override
    public void OnDengLoginUsseccd(LoginBean loginBean) {

    }

    @Override
    public void OnRegLoginUsseccd(RegBean regBean) {
        if (regBean.getCode().equals("0")){
            Toast.makeText(RegActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(RegActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnUploadUsseccd(UploadBean uploadBean) {

    }

    @Override
    public void OngetUserInfo(UserInfoBean userInfoBean) {

    }

    @Override
    public void OnNickname(NickBean nickBean) {

    }

    @Override
    public void OnOrdersLogin(OrdersBean ordersBean) {

    }

    @Override
    public void onLoginFaild(String error) {

    }

    @Override
    public void OnUpOrderUsseccd(UpOrderBean upOrderBean) {

    }
}
