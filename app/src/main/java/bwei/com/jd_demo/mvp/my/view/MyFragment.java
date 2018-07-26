package bwei.com.jd_demo.mvp.my.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.activity.LoginActivity;
import bwei.com.jd_demo.activity.OrderActivity;
import bwei.com.jd_demo.activity.UserActivity;
import bwei.com.jd_demo.addr.view.TackActivity;

public class MyFragment extends Fragment {

    @BindView(R.id.my_touxiang)
    SimpleDraweeView myTouxiang;
    @BindView(R.id.my_name)
    TextView myName;
    Unbinder unbinder;
    @BindView(R.id.my_dingdan)
    ImageView myDingdan;
    @BindView(R.id.my_shouhuo)
    ImageView myShouhuo;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int uid;
    private Uri uri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.my_fregment, container, false);
        myTouxiang = view.findViewById(R.id.my_touxiang);
        uri = Uri.parse("res://bwei.com.jd_demo/" + R.drawable.mqq);
        myTouxiang.setImageURI(uri);
        unbinder = ButterKnife.bind(this, view);
        SharedPreferences read = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        final String name = read.getString("name", "");
        final String coin = read.getString("coin", "");
        uid = read.getInt("uid", 0);
        Log.d("name", "name+++" + name);
        Log.d("coin", "coin+++" + coin);
        Log.d("uid", "uid+++" + uid);
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(coin)) {
            myName.setText(name);
            myTouxiang.setImageURI(coin);
        }
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshLayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshLayout.finishLoadmore(2000);
            }
        });
        refreshLayout.setRefreshHeader(new PhoenixHeader(getContext()));
        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 0) {
            myName.setText("登录/注册>");
            myTouxiang.setImageURI(uri);
        }
        if (requestCode == 1 && resultCode == 1) {
            String name = data.getStringExtra("mobile");
            String image = data.getStringExtra("image");
            myName.setText(name);
            myTouxiang.setImageURI(image);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.my_touxiang)
    public void onViewClicked() {
        if (myName.getText().toString().equals("登录/注册>")) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        } else {
            Intent intent = new Intent(getActivity(), UserActivity.class);
            startActivityForResult(intent, 1);
        }

    }

    @OnClick({R.id.my_dingdan, R.id.my_shouhuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_dingdan:
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
                break;
            case R.id.my_shouhuo:
                Intent intent1 = new Intent(getActivity(), TackActivity.class);
                intent1.putExtra("uid", uid);
                startActivity(intent1);
                break;
        }
    }
}
