package bwei.com.jd_demo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.mvp.fen.view.FenFragment;
import bwei.com.jd_demo.mvp.my.model.bean.LoginBean;
import bwei.com.jd_demo.mvp.my.model.bean.NickBean;
import bwei.com.jd_demo.mvp.my.model.bean.OrdersBean;
import bwei.com.jd_demo.mvp.my.model.bean.RegBean;
import bwei.com.jd_demo.mvp.my.model.bean.UpOrderBean;
import bwei.com.jd_demo.mvp.my.model.bean.UploadBean;
import bwei.com.jd_demo.mvp.my.model.bean.UserInfoBean;
import bwei.com.jd_demo.mvp.my.persenter.MyPersenter;
import bwei.com.jd_demo.mvp.my.view.MyFragment;
import bwei.com.jd_demo.mvp.my.view.MyView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class LoginActivity extends AppCompatActivity implements MyView {

    @BindView(R.id.my_login_mm)
    EditText myLoginMm;
    @BindView(R.id.my_login_zh)
    EditText myLoginZh;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.my_login_reg)
    TextView myLoginReg;
    @BindView(R.id.my_login_weixin)
    ImageView myLoginWeixin;
    @BindView(R.id.my_login_qq)
    ImageView myLoginQq;
    @BindView(R.id.login_button)
    Button loginButton;
    private MyPersenter myPersenter;
    private String zh;
    private String mm;
    private String mobile;
    private FileOutputStream fileOutputStream;
    private String icon;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_login);
        myPersenter = new MyPersenter(this);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.my_login_reg, R.id.my_login_weixin, R.id.my_login_qq, R.id.login_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_login_reg:
            startActivity(new Intent(LoginActivity.this,RegActivity.class));
                break;
            case R.id.my_login_weixin:
                break;
            case R.id.my_login_qq:
                break;
            case R.id.login_button:
                zh = myLoginZh.getText().toString();
                mm = myLoginMm.getText().toString();
                Log.d("账号密码","账号，mm++++"+ zh +"--"+ mm);
                myPersenter.login(zh, mm);
                final File file = getResourseFile();
                final RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                final MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                myPersenter.UploadLogin(14763,part);
                if (!zh.equals("")){
                    finish();
                }else{
                    Log.d("不能为空","ssss"+zh);
                }
                break;
        }
    }

    @Override
    public void onLoginFaild(String error) {
        Toast.makeText(LoginActivity.this,"登录失败--",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnUpOrderUsseccd(UpOrderBean upOrderBean) {

    }

    @Override
    public void OnDengLoginUsseccd(LoginBean loginBean) {
        Toast.makeText(LoginActivity.this,loginBean.getMsg().toString(),Toast.LENGTH_SHORT).show();
                Log.d("数据","loginbean++++"+loginBean.toString()+loginBean.getData().toString());
                LoginBean.DataBean data = loginBean.getData();
                mobile = data.getMobile();
                icon=data.getIcon();
                 uid = data.getUid();

        SharedPreferences pref = LoginActivity.this.getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putInt("uid",uid);
                edit.putString("name", mobile);
                edit.putString("coin",icon);
                edit.commit();

    }

    @Override
    public void OnRegLoginUsseccd(RegBean regBean) {

    }

    @Override
    public void OnUploadUsseccd(UploadBean uploadBean) {
        if (uploadBean!=null&&"0".equals(uploadBean.getCode())){
            Toast.makeText(LoginActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
        }else{
            Log.e("tag", "失败" + uploadBean.toString());
            Toast.makeText(LoginActivity.this, "错误", Toast.LENGTH_SHORT).show();
        }
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

    private File getResourseFile(){
    final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
    final File file = new File(getFilesDir().getAbsolutePath());
    if (!file.exists()){
        file.mkdirs();
    }
    final File file1 = new File(file, "photo.png");
    try {
        fileOutputStream = new FileOutputStream(file1);
        bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return file1;
}

}
