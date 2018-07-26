package bwei.com.jd_demo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.security.PublicKey;

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
import bwei.com.jd_demo.mvp.my.view.MyFragment;
import bwei.com.jd_demo.mvp.my.view.MyView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserActivity extends AppCompatActivity implements MyView {

    @BindView(R.id.user_image)
    SimpleDraweeView userImage;
    @BindView(R.id.user_image_xg)
    TextView userImageXg;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_name_xiu)
    TextView userNameXiu;
    @BindView(R.id.user_mobile)
    TextView userMobile;
    @BindView(R.id.user_time)
    TextView userTime;
    @BindView(R.id.user_back)
    TextView userBack;
    @BindView(R.id.login_zhuxiao)
    Button loginZhuxiao;
    private MyPersenter myPersenter;
    private int uid;
    private String nickname;
    private String name;
    private EditText dialogname;
    private String path = Environment.getExternalStorageDirectory() + "/camera.png";
    private Bitmap bitmap;
    private String icon;
    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);
        uid = data.getInt("uid", 0);
        myPersenter = new MyPersenter(this);
        myPersenter.UserLogin(uid);
        loginZhuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.clear();
                edit.commit();
                setResult(0,intent);
                finish();
            }
        });
    }

    @Override
    public void OnDengLoginUsseccd(LoginBean loginBean) {

    }

    @Override
    public void OnRegLoginUsseccd(RegBean regBean) {

    }

    @Override
    public void OnUploadUsseccd(UploadBean uploadBean) {

    }

    @Override
    public void OngetUserInfo(UserInfoBean userInfoBean) {
        UserInfoBean.DataBean list = userInfoBean.getData();
        nickname = list.getNickname();
        mobile = list.getMobile();
        icon = list.getIcon();
        String createtime = list.getCreatetime();
        userImage.setImageURI(icon);
        userMobile.setText(mobile);
        userName.setText(nickname);
        userTime.setText(createtime);
    }


    @Override
    public void onLoginFaild(String error) {

    }

    @Override
    public void OnUpOrderUsseccd(UpOrderBean upOrderBean) {

    }

    @OnClick({R.id.user_image_xg, R.id.user_name_xiu, R.id.user_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_image_xg:
                alertdialog();
                break;
            case R.id.user_name_xiu:
                View inflate = View.inflate(UserActivity.this, R.layout.alertdialog, null);
                dialogname = inflate.findViewById(R.id.dialog_name);
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                builder.setTitle("修改名称");
                builder.setView(inflate);
                builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        name = dialogname.getText().toString();
                        myPersenter.NickLogin(uid, name);
                        userName.setText(name);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
                break;
            case R.id.user_back:
                Intent intent = new Intent();
                intent.putExtra("mobile",mobile);
                intent.putExtra("image",icon);
                setResult(1,intent);
                finish();
                break;
        }
    }

    private void alertdialog() {
        View inflate1 = View.inflate(UserActivity.this, R.layout.image_update, null);
        Button camera = inflate1.findViewById(R.id.image_update_camera);
        Button photoAlbum = inflate1.findViewById(R.id.image_update_photo);
        AlertDialog.Builder builder1=new AlertDialog.Builder(this);
        builder1.setView(inflate1);
        builder1.setNegativeButton("取消",null);
        builder1.show();
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(intent,1);
            }
        });
        photoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setType("image/*");
                startActivityForResult(intent,4);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==RESULT_OK){
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(Uri.fromFile(new File(path)),"image/*");
            intent.putExtra("crop",true);
            intent.putExtra("aspactX",1);
            intent.putExtra("outputX",250);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, 3);
        }
        if (requestCode==4&&resultCode==RESULT_OK){
            //获取路径的Uri
            Uri uri = data.getData();
            Intent intent = new Intent("com.android.camera.action.CROP");
            //进行裁剪
            intent.setDataAndType(uri, "image/*");
            //设置是否支持裁剪
            intent.putExtra("crop", true);
            //设置宽高
            intent.putExtra("aspactX", 1);
            intent.putExtra("aspactY", 1);
            //设置输出图片的大小
            intent.putExtra("outputX", 250);
            intent.putExtra("outputY", 250);
            //设置讲图片进行返回
            intent.putExtra("return-data", true);
            startActivityForResult(intent, 3);
        }
        if (requestCode==3&&resultCode==RESULT_OK){
            bitmap = data.getParcelableExtra("data");
            userImage.setImageBitmap(bitmap);
            File file = getResourseFile(bitmap);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part part=MultipartBody.Part.createFormData("file",file.getName(),requestBody);
            myPersenter.UploadLogin(uid,part);
        }
    }
    private File getResourseFile(Bitmap bitmap){
        File file = new File(getCacheDir().getAbsolutePath());
        if (!file.exists()){
            file.mkdirs();
        }
        File file1 = new File(file, "photo.png");
        FileOutputStream fileOutputStream=null;
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
    @Override
    public void OnNickname(NickBean nickBean) {
        if (nickBean.getCode().equals("0")) {
            Toast.makeText(UserActivity.this, "修改成功" + name, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnOrdersLogin(OrdersBean ordersBean) {

    }
}
