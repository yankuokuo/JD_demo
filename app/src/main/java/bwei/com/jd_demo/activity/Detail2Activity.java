package bwei.com.jd_demo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.mvp.car.model.bean.AddBean;
import bwei.com.jd_demo.mvp.home.model.bean.DetailBean;
import bwei.com.jd_demo.mvp.home.model.bean.JiuBean;
import bwei.com.jd_demo.mvp.home.model.bean.LunBean;
import bwei.com.jd_demo.mvp.home.model.bean.SearBean;
import bwei.com.jd_demo.mvp.home.persenter.HomePersenter;
import bwei.com.jd_demo.mvp.home.view.ILoginView;

public class Detail2Activity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.detail_banner)
    Banner detailBanner;
    @BindView(R.id.detail_price)
    TextView detailPrice;
    @BindView(R.id.detail_name)
    TextView detailName;
    @BindView(R.id.detail_youhui)
    TextView detailYouhui;
    @BindView(R.id.detail_button)
    Button detailButton;
    private List<String>images=new ArrayList<>();
    private HomePersenter homePersenter;
    private int searpid;
    boolean TRUE;
    boolean FLASE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        homePersenter = new HomePersenter(this);
        Intent intent = getIntent();
        searpid = intent.getIntExtra("searpid", 0);
        Log.d("传过来的searpid","searpid+++++"+ searpid);
            homePersenter.detailLogin(searpid);
    }

    @Override
    public void onLoginSuccess(LunBean lunBean) {

    }

    @Override
    public void onjiuLoginSuccess(JiuBean jiuBean) {

    }

    @Override
    public void onsearLoginSucess(SearBean searBean) {

    }

    @Override
    public void onDetailLoginSucess(DetailBean detailBean) {
        DetailBean.DataBean data = detailBean.getData();
        Log.d("打印出来的data","data---"+data);
        detailPrice.setText("￥"+data.getPrice()+"");
        detailName.setText(data.getTitle());
        detailYouhui.setText(data.getSubhead());
            String[] split1 = data.getImages().split("\\|");
            images.add(split1[0]);
            detailBanner.setImageLoader(new GildeImageLoder());
            detailBanner.setImages(images);
            detailBanner.start();
    }



    @Override
    public void onerror(String error) {

    }
    @OnClick(R.id.detail_button)
    public void onViewClicked() {
        SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);
        int uid = data.getInt("uid", 0);
        homePersenter.addLogin(uid,searpid);
        //homePersenter.addLogin(uid,searpid);
    }
    public class GildeImageLoder extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }

        @Override
        public ImageView createImageView(Context context) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
            return simpleDraweeView;
        }
    }
    @Override
    public void addUsseccd(AddBean addBean) {
        if (addBean.getCode().equals("0")){
            Toast.makeText(Detail2Activity.this,"加入购物车成功",Toast.LENGTH_SHORT).show();
        }
    }
}
