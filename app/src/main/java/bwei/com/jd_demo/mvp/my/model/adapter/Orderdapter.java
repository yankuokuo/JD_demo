package bwei.com.jd_demo.mvp.my.model.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.IdentityHashMap;
import java.util.List;

import bwei.com.jd_demo.R;
import bwei.com.jd_demo.mvp.fen.model.bean.ChildBean;
import bwei.com.jd_demo.mvp.my.model.bean.OrdersBean;

import static android.content.Context.MODE_PRIVATE;

public class Orderdapter extends RecyclerView.Adapter<Orderdapter.ViewHolder> {
    private  List<OrdersBean.DataBean> list;
    private Context context;
    private int uid;

    public Orderdapter(List<OrdersBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Orderdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.oders_itme, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Orderdapter.ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(list.get(i).getTitle());
        viewHolder.price.setText("$"+list.get(i).getPrice()+"");
        viewHolder.time.setText(list.get(i).getOrderid() +"");
        int status = list.get(i).getStatus();
        uid = list.get(i).getUid();

        SharedPreferences data = context.getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor edit = data.edit();
        edit.putInt("uuid", uid);
        edit.commit();
        if (status ==0){
            viewHolder.status.setText("待支付");
            viewHolder.status.setTextColor(Color.BLACK);
        }
        if(status ==1) {
            viewHolder.status.setText("已支付");
            viewHolder.status.setTextColor(Color.RED);
        }
        if(status ==2){
            viewHolder.status.setText("已取消");
            viewHolder.status.setTextColor(Color.BLUE);
        }
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int orderid = list.get(i).getOrderid();
                onButtonClickListener.onItemClick(view,orderid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name,price,time;
        private final TextView status;
        private final Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id._orders_name);
            price = itemView.findViewById(R.id._orders_price);
            time=itemView.findViewById(R.id._orders_time);
            status = itemView.findViewById(R.id._orders_status);
            button = itemView.findViewById(R.id.orders_button);
        }
    }
    public interface OnButtonClickListener{
        void onItemClick(View view,int orderid);
    }
    OnButtonClickListener onButtonClickListener;
    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener){
        this.onButtonClickListener=onButtonClickListener;
    }
}
