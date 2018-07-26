package bwei.com.jd_demo.mvp.car.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.mvp.car.addbutton.MyAddSubView;
import bwei.com.jd_demo.mvp.car.model.bean.CarBean;

public class CarparentAdapter extends BaseExpandableListAdapter {
    private List<CarBean.DataBean> list;
    private Context context;
    private List<CarBean.DataBean.ListBean> data;

    public CarparentAdapter(List<CarBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list.get(i).getList().size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        ParentViewHolder holder;
        if (view==null){
            view = View.inflate(viewGroup.getContext(), R.layout.car_parent, null);
            holder=new ParentViewHolder(view);
            view.setTag(holder);
        }else{
            holder= (ParentViewHolder) view.getTag();
        }
        //赋值商家名称
        holder.parentSellerNameTv.setText(list.get(i).getSellerName());
        //赋值商家选中状态
        boolean ischeak = ischeak(i);
        holder.parentSellerCb.setChecked(ischeak);
        //点击商家的check
        holder.parentSellerCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //通过接口传过去
                if (oncheckchanglister!=null){
                    oncheckchanglister.onparentcheckchange(i);
                }
            }
        });
        return view;
    }
    //判断商家的选中状态
    public boolean ischeak(int i){
        CarBean.DataBean dataBean = list.get(i);
        List<CarBean.DataBean.ListBean> list = dataBean.getList();
        for (CarBean.DataBean.ListBean list1: list) {
            //遍历child，被选中零个则商家没选中
            if (list1.getSelected()==0){
                return false;
            }
        }
        return true;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder holder;
        if (view==null){
            view = View.inflate(viewGroup.getContext(), R.layout.car_child, null);
            holder=new ChildViewHolder(view);
            view.setTag(holder);
        }else{
            holder= (ChildViewHolder) view.getTag();
        }
        data = this.list.get(i).getList();
        CarBean.DataBean.ListBean listBean = data.get(i1);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pid = data.get(i1).getPid();
                oncheckchanglister.ondelete(pid);
            }
        });
        holder.dingdanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float price = data.get(i1).getPrice();
                oncheckchanglister.ondiangdanCheckchange(price);
            }
        });
       //赋值商品名称
        holder.carChildTitie.setText(listBean.getTitle());
        //赋值商家的价格
        holder.carChildPrice.setText("价格："+listBean.getPrice()+"元");
        String[] split = listBean.getImages().split("\\|");
        Uri uri = Uri.parse(split[0]);
        //通过frsco赋值商品图片
        holder.simpleDraweeView.setImageURI(uri);
        //赋值商品数量
        holder.number.setNumber(listBean.getNum());
        //赋值商品选中状态
        holder.carChildCb.setChecked(listBean.getSelected()==1);
        //点击商品的check
        holder.carChildCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //传过去
                if (oncheckchanglister!=null){
                    oncheckchanglister.onchildcheckchange(i, i1);
                }
            }
        });
        holder.number.setOnNumBerChangeListener(new MyAddSubView.OnNumBerChangeListener() {
            @Override
            public void onNumBerChange(int num) {
                if (oncheckchanglister!=null){
                    oncheckchanglister.onNumBerchange(i,i1,num);
                }
            }
        });
        return view;
    }
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
    //判断所有商品是否被选中
    public boolean isAllProductselected(){
        for (int i = 0; i <list.size() ; i++) {
            List<CarBean.DataBean.ListBean> list = this.list.get(i).getList();
            for (int j = 0; j <list.size(); j++) {
                //遍历商品的选中状态为零则为false
                if (list.get(j).getSelected()==0){
                    return false;
                }
            }
        }
        return true;
    }
    //当商品被点击时
    public void changchildcheckbox(int i,int i1){
        CarBean.DataBean dataBean = list.get(i);
        List<CarBean.DataBean.ListBean> list = dataBean.getList();
        CarBean.DataBean.ListBean listBean = list.get(i1);
        //赋选中状态
        listBean.setSelected(listBean.getSelected()==0?1:0);
    }
    //设置所有商品的状态
    public void changAllproductstatus(boolean b){
        for (int i = 0; i <list.size() ; i++) {
            List<CarBean.DataBean.ListBean> list = this.list.get(i).getList();
            for (int j = 0; j <list.size(); j++) {
                //全选或全不选
                list.get(j).setSelected(b?1:0);
            }
        }
    }
    //当商家点击时
    public void changCurrentSellerAllProdStatus(int i,boolean b){
        CarBean.DataBean dataBean = list.get(i);
        List<CarBean.DataBean.ListBean> list1 = dataBean.getList();
        for (int j = 0; j <list1.size() ; j++) {
            CarBean.DataBean.ListBean listBean = list1.get(j);
            listBean.setSelected(b?1:0);
        }
    }
    //计算总价
    public float calculateToalPrice(){
        float totalprice=0;
        for (int i = 0; i <list.size() ; i++) {
            List<CarBean.DataBean.ListBean> list = this.list.get(i).getList();
            for (int j = 0; j <list.size() ; j++) {
                if (list.get(j).getSelected()==1){
                    float price = list.get(j).getPrice();
                    int num = list.get(j).getNum();
                    totalprice+=price*num;
                }
            }
        }
        return totalprice;
    }
    //结算
    public int calculToalNumBar(){
        int toNumBar=0;
        for (int i = 0; i <list.size() ; i++) {
            List<CarBean.DataBean.ListBean> list = this.list.get(i).getList();
            for (int j = 0; j <list.size() ; j++) {
                if (list.get(j).getSelected()==1){
                    int num = list.get(j).getNum();
                    toNumBar+=num;
                }
            }
        }
        return toNumBar;
    }
    //加减器改变数量
    public void changNumBarstatus(int i,int i1,int num){
        CarBean.DataBean dataBean = list.get(i);
        List<CarBean.DataBean.ListBean> list = dataBean.getList();
        CarBean.DataBean.ListBean listBean = list.get(i1);
        listBean.setNum(num);
    }
     class ParentViewHolder {
        @BindView(R.id.parent_seller_cb)
        CheckBox parentSellerCb;
        @BindView(R.id.parent_seller_name_tv)
        TextView parentSellerNameTv;

         ParentViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ChildViewHolder {
        @BindView(R.id.dingdan_login)
        Button dingdanbtn;
        @BindView(R.id.car_login_button)
        Button button;
        @BindView(R.id.car_child_cb)
        CheckBox carChildCb;
        @BindView(R.id.car_child_titie)
        TextView carChildTitie;
        @BindView(R.id.car_child_price)
        TextView carChildPrice;
        @BindView(R.id.car_child_image)
        SimpleDraweeView simpleDraweeView;
        @BindView(R.id.car_child_number)
        MyAddSubView number;
        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    Oncheckchanglister oncheckchanglister;
    public void setOncheckchangelister(Oncheckchanglister oncheckchangelister){
        this.oncheckchanglister=oncheckchangelister;
    }
    public interface Oncheckchanglister{
        //商家CheckBox
        void onparentcheckchange(int i);
        //商品checkBox
        void onchildcheckchange(int i,int i1);
        //按钮价钱
        void onNumBerchange(int i,int i1,int num);
        void ondelete(int pid);
        void ondiangdanCheckchange(float price);
    }
}
