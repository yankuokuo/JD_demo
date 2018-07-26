package bwei.com.jd_demo.mvp.home.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sunfusheng.marqueeview.MarqueeView;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.activity.DetailActivity;
import bwei.com.jd_demo.activity.ExitActivity;
import bwei.com.jd_demo.activity.SearchActivity;
import bwei.com.jd_demo.base.BaseActivity;
import bwei.com.jd_demo.mvp.car.model.bean.AddBean;
import bwei.com.jd_demo.mvp.home.adpter.JiuAdapter;
import bwei.com.jd_demo.mvp.home.adpter.MiaoAdapter;
import bwei.com.jd_demo.mvp.home.adpter.TuiAdapter;
import bwei.com.jd_demo.mvp.home.model.bean.DetailBean;
import bwei.com.jd_demo.mvp.home.model.bean.JiuBean;
import bwei.com.jd_demo.mvp.home.model.bean.LunBean;
import bwei.com.jd_demo.mvp.home.model.bean.SearBean;
import bwei.com.jd_demo.mvp.home.persenter.HomePersenter;

public class HomeFragment extends BaseActivity<HomePersenter> implements ILoginView{

    @BindView(R.id.home_banner)
    Banner homeBanner;
    @BindView(R.id.home_recyclerview)
    RecyclerView homeRecyclerview;
    Unbinder unbinder;
    @BindView(R.id.home_miaosha)
    RecyclerView homeMiaosha;
    @BindView(R.id.home_tuijian)
    RecyclerView homeTuijian;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.tou_shaomiao)
    ImageView touShaomiao;
    @BindView(R.id.tou_shuru)
    EditText touShuru;
    @BindView(R.id.tou_xiaoxi)
    ImageView touXiaoxi;
    Unbinder unbinder1;

    private List<String> images = new ArrayList<>();

    @Override
    protected HomePersenter providePresenter() {
        HomePersenter homePersenter = new HomePersenter(this);
        homePersenter.login1();
        return homePersenter;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        marqueeView = view.findViewById(R.id.marqueeView);
        List<String> info = new ArrayList<>();
        info.add("有人向你表示了讨厌");
        info.add("并向你丢了一个蕾姆酱");
        info.add("嘿嘿嘿");
        info.add("哈哈哈 !");
        // 在代码里设置自己的动画
        marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.home_fregment;
    }


    @Override
    public void onLoginSuccess(LunBean lunBean) {
        //轮播图
        List<LunBean.DataBean> data = lunBean.getData();
        for (int i = 0; i < data.size(); i++) {
            String icon = data.get(i).getIcon();
            String[] split = icon.split("\\|");
            images.add(split[0]);
            homeBanner.setImageLoader(new GildeImageLoder());
            homeBanner.setImages(images);
            homeBanner.start();
        }
        //秒杀
        final List<LunBean.MiaoshaBean.ListBeanX> list = lunBean.getMiaosha().getList();
        MiaoAdapter miaoAdapter = new MiaoAdapter(list, getContext());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeMiaosha.setLayoutManager(linearLayoutManager);
        homeMiaosha.setAdapter(miaoAdapter);
        miaoAdapter.setOnItemClickListener(new MiaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pid) {
//                Intent intent = new Intent(getActivity(), DetailActivity.class);
//                intent.putExtra("miaopid",pid);
//                startActivity(intent);
                Log.d("秒杀的id","+++++++"+pid);
               // Toast.makeText(getActivity(),pid,Toast.LENGTH_SHORT).show();
            }
        });
        //推荐
        final List<LunBean.TuijianBean.ListBean> list1 = lunBean.getTuijian().getList();
        TuiAdapter tuiAdapter = new TuiAdapter(list1, getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        homeTuijian.setLayoutManager(gridLayoutManager);
        homeTuijian.setAdapter(tuiAdapter);
        tuiAdapter.setOnItemClickListener(new TuiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pid) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("tuipid",pid);
                startActivity(intent);
            }
        });
    }


    //九宫格
    @Override
    public void onjiuLoginSuccess(JiuBean jiuBean) {
        List<JiuBean.DataBean> list = jiuBean.getData();
        JiuAdapter jiuAdapter = new JiuAdapter(list, getActivity());
        GridLayoutManager gridLagyoutManager = new GridLayoutManager(getContext(), 2);
        gridLagyoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeRecyclerview.setLayoutManager(gridLagyoutManager);
        homeRecyclerview.setAdapter(jiuAdapter);
       jiuAdapter.setOnItemClickListener(new JiuAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(View view, int cid) {
               Intent intent = new Intent(getActivity(), SearchActivity.class);
               intent.putExtra("jiucid", cid);
               startActivity(intent);
           }
       });
    }

    @Override
    public void onsearLoginSucess(SearBean searBean) {

    }

    @Override
    public void onDetailLoginSucess(DetailBean detailBean) {

    }

    @Override
    public void addUsseccd(AddBean addBean) {

    }

    @Override
    public void onerror(String error) {
        Log.d("sss", "sss");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @OnClick({R.id.tou_shaomiao, R.id.tou_shuru, R.id.tou_xiaoxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tou_shaomiao:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivity(intent);
                break;
            case R.id.tou_shuru:
            startActivity(new Intent(getContext(), ExitActivity.class));
                break;
            case R.id.tou_xiaoxi:
                break;
        }
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
}
