package bwei.com.jd_demo.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.mvp.my.model.adapter.Orderdapter;
import bwei.com.jd_demo.mvp.my.model.bean.LoginBean;
import bwei.com.jd_demo.mvp.my.model.bean.NickBean;
import bwei.com.jd_demo.mvp.my.model.bean.OrdersBean;
import bwei.com.jd_demo.mvp.my.model.bean.RegBean;
import bwei.com.jd_demo.mvp.my.model.bean.UpOrderBean;
import bwei.com.jd_demo.mvp.my.model.bean.UploadBean;
import bwei.com.jd_demo.mvp.my.model.bean.UserInfoBean;
import bwei.com.jd_demo.mvp.my.persenter.MyPersenter;
import bwei.com.jd_demo.mvp.my.view.MyView;

public class OrderActivity extends AppCompatActivity implements MyView {

    @BindView(R.id.orders_recyclerview)
    RecyclerView ordersRecyclerview;
    @BindView(R.id.orders_total_price)
    TextView ordersTotalPrice;
    @BindView(R.id.orders_pay)
    Button ordersPay;
    private MyPersenter myPersenter;
    private PopupWindow popupWindow;
    private TextView popo0;
    private TextView popo1;
    private TextView popo2;
    private int uuid;
    private Orderdapter orderdapter;
    private int uid;
    private SharedPreferences data;
    private int qq;
    private double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        uid = intent.getIntExtra("uid", 0);
        myPersenter = new MyPersenter(this);
        myPersenter.Orders(uid);
        data = getSharedPreferences("data", MODE_PRIVATE);
        uuid = data.getInt("uuid", 0);
        View view = View.inflate(this, R.layout.popuwindow, null);
        popo0 = view.findViewById(R.id.popo_0);
        popo1 = view.findViewById(R.id.popo_1);
        popo2 = view.findViewById(R.id.popo_2);
        popo0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPersenter.UpOrderLogin(uuid,0+"",qq);
                popupWindow.dismiss();
            }
        });
        popo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPersenter.UpOrderLogin(uuid,1+"",qq);
                popupWindow.dismiss();
            }
        });
        popo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPersenter.UpOrderLogin(uuid,2+"",qq);
                popupWindow.dismiss();
            }
        });
        popupWindow = new PopupWindow(view, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setFocusable(true);
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

    }

    @Override
    public void OnNickname(NickBean nickBean) {

    }

    @Override
    public void OnOrdersLogin(OrdersBean ordersBean) {
        List<OrdersBean.DataBean> list = ordersBean.getData();
        for (int i = 0; i <list.size() ; i++) {
            price = list.get(i).getPrice();
        }
        int prices= (int) +price;
        ordersTotalPrice.setText("￥"+prices+"元");
        ordersTotalPrice.setTextColor(Color.RED);
        ordersTotalPrice.setTextSize(30);
        orderdapter = new Orderdapter(list, OrderActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ordersRecyclerview.setLayoutManager(linearLayoutManager);
        ordersRecyclerview.setAdapter(orderdapter);
        orderdapter.setOnButtonClickListener(new Orderdapter.OnButtonClickListener() {
            @Override
            public void onItemClick(View view,int orderid) {
                qq = orderid;
                popupWindow.showAsDropDown(view);
            }
        });

    }

    @Override
    public void onLoginFaild(String error) {

    }

    @Override
    public void OnUpOrderUsseccd(UpOrderBean upOrderBean) {

        if (upOrderBean.getCode().equals("0")){
            Toast.makeText(OrderActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
            myPersenter.Orders(uid);
            orderdapter.notifyDataSetChanged();
        }

    }

    @OnClick(R.id.orders_pay)
    public void onViewClicked() {
    }
}
