package bwei.com.jd_demo.mvp.car.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.activity.OrderActivity;
import bwei.com.jd_demo.base.BaseActivity;
import bwei.com.jd_demo.mvp.car.adapter.CarparentAdapter;
import bwei.com.jd_demo.mvp.car.model.bean.AddBean;
import bwei.com.jd_demo.mvp.car.model.bean.CarBean;
import bwei.com.jd_demo.mvp.car.model.bean.CreateBean;
import bwei.com.jd_demo.mvp.car.model.bean.DeleteBean;
import bwei.com.jd_demo.mvp.car.model.bean.UpdataCartBean;
import bwei.com.jd_demo.mvp.car.persenter.CarPersenter;
import bwei.com.jd_demo.mvp.fen.adpter.ChildAdapter;

public class CarFragment extends BaseActivity<CarPersenter> implements CarView {

    @BindView(R.id.car_expandableview)
    ExpandableListView carExpandableview;
    @BindView(R.id.cart_all_select)
    CheckBox cartAllSelect;
    @BindView(R.id.tv_cart_total_price)
    TextView tvCartTotalPrice;
    @BindView(R.id.btn_cart_pay)
    Button btnCartPay;
    Unbinder unbinder;
    private CarparentAdapter carparentAdapter;
    private int uid;
    private int pid;
    private List<CarBean.DataBean> list;

    @Override
    protected int provideLayoutId() {
        return R.layout.car_fregment;
    }
    @Override
    protected CarPersenter providePresenter() {
      /*  CarPersenter carPersenter = new CarPersenter(this);*/
        return new CarPersenter(this);
    }


    @Override
    protected void initView(View view) {
        SharedPreferences data = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        uid = data.getInt("uid",0);
        pid = data.getInt("pid",0);
        Log.d("Uid和pid","++++++++"+ this.uid +"--"+pid);
    }
    @Override
    protected void initData() {
        presenter.carLogin(uid);
    }


    @Override
    public void onCarUssecssd(final CarBean carBean) {
        Log.d("car", "car++++++" + carBean.getData());
        list = carBean.getData();
        carparentAdapter = new CarparentAdapter(list, getContext());
        carparentAdapter.setOncheckchangelister(new CarparentAdapter.Oncheckchanglister() {

            private int selected;
            private int num;
            private int pid1;

            @Override
            public void onparentcheckchange(int i) {
                boolean ischeak = carparentAdapter.ischeak(i);
                //设置商家被点时商品的状态
                carparentAdapter.changCurrentSellerAllProdStatus(i,!ischeak);
                carparentAdapter.notifyDataSetChanged();
                List<CarBean.DataBean.ListBean> data = CarFragment.this.list.get(i).getList();
                for (int j = 0; j <data.size(); j++) {
                    pid1 = data.get(j).getPid();
                    num = data.get(j).getNum();
                    selected = data.get(j).getSelected();
                }
                presenter.UpdateLogin(uid,list.get(i).getSellerid(),pid1,num,selected);
                refresh();
            }

            @Override
            public void onchildcheckchange(int i, int i1) {
                carparentAdapter.changchildcheckbox(i,i1);
                carparentAdapter.notifyDataSetChanged();
                presenter.UpdateLogin(uid,list.get(i).getSellerid(),list.get(i).getList().get(i1).getPid(),list.get(i).getList().get(i1).getNum(),list.get(i).getList().get(i1).getSelected());
                refresh();
            }

            @Override
            public void onNumBerchange(int i, int i1, int num) {
                carparentAdapter.changNumBarstatus(i,i1,num);
                carparentAdapter.notifyDataSetChanged();
                presenter.UpdateLogin(uid,list.get(i).getSellerid(),list.get(i).getList().get(i1).getPid(),list.get(i).getList().get(i1).getNum(),list.get(i).getList().get(i1).getSelected());
                refresh();
            }

            @Override
            public void ondelete(int pid) {
                presenter.DeleteLogin(uid,pid);
                Log.d("删除的uid和pid","qq"+uid+"---"+pid);
            }

            @Override
            public void ondiangdanCheckchange(float price) {
                presenter.CreateLogin(uid,price);
            }

        });
        carExpandableview.setAdapter(carparentAdapter);
        for (int i = 0; i < list.size() ; i++) {
            carExpandableview.expandGroup(i);
        }
        refresh();
    }

    @Override
    public void onerror(String error) {
        Log.d("sss","sss");
    }

    private void refresh(){
        boolean allProductselected = carparentAdapter.isAllProductselected();
        //赋值所有商品被选中
        cartAllSelect.setChecked(allProductselected);
        float toalPrice = carparentAdapter.calculateToalPrice();
        //赋值总价：数量乘价格
        tvCartTotalPrice.setText("总价:"+toalPrice);
        //赋值结算所有的数量
        int toalNumBar = carparentAdapter.calculToalNumBar();
        btnCartPay.setText("去结算（"+toalNumBar+")");
    }

    @OnClick({R.id.cart_all_select, R.id.btn_cart_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cart_all_select:
                //获取到所有商品选中状态
                boolean allProductselected = carparentAdapter.isAllProductselected();
                //设置所有商品的状态
                carparentAdapter.changAllproductstatus(!allProductselected);
                carparentAdapter.notifyDataSetChanged();
                refresh();
                break;
            case R.id.btn_cart_pay:
                Toast.makeText(getContext(),"去结算",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    public void DeleteUssecd(DeleteBean deleteBean) {
        if (deleteBean.getCode().equals("0")){
            presenter.carLogin(uid);
        }
    }

    @Override
    public void UpdateUseecd(UpdataCartBean updataCartBean) {
        if (updataCartBean.getCode().equals("0")){
            Toast.makeText(getActivity(),"更新成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void CreateUseccd(CreateBean createBean) {
        if (createBean.getCode().equals("0")){
            Toast.makeText(getActivity(),"加入成功",Toast.LENGTH_SHORT).show();
        }
    }
}
