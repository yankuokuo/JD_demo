package bwei.com.jd_demo.addr.model.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import bwei.com.jd_demo.R;
import bwei.com.jd_demo.addr.model.bean.GetAddBean;
import bwei.com.jd_demo.mvp.my.model.bean.OrdersBean;

import static android.content.Context.MODE_PRIVATE;

public class Addrdapter extends RecyclerView.Adapter<Addrdapter.ViewHolder> {
    private  List<GetAddBean.DataBean> list;
    private Context context;
    boolean aa;
    public Addrdapter(List<GetAddBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Addrdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tack_itme, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Addrdapter.ViewHolder viewHolder, final int i) {


        final int status = list.get(i).getStatus();
        if (status==0){
            aa=false;
        }
        if (status==1){
            aa=true;
        }
        viewHolder.name.setText(list.get(i).getName());
        viewHolder.mobile.setText(list.get(i).getMobile()+"");
        viewHolder.addr.setText(list.get(i).getAddr());
        viewHolder.checkBox.setChecked(aa);
        viewHolder.update.setOnClickListener(new View.OnClickListener() {
            private int addrid;
            @Override
            public void onClick(View view) {
                addrid = list.get(i).getAddrid();
                onButtonClickListener.onItemClick(view, addrid);
            }
        });
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            private int addrid;
            @Override
            public void onClick(View view) {
                addrid = list.get(i).getAddrid();
                onButtonClickListener.onCheckClick(view,addrid,status);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name,mobile,addr,update;
        private CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.addr_item_name);
            mobile = itemView.findViewById(R.id.addr_item_mobile);
            addr=itemView.findViewById(R.id.addr_item_addr);
            update = itemView.findViewById(R.id.addr_update);
            checkBox = itemView.findViewById(R.id.addr_checkbox);
        }
    }
    public interface OnButtonClickListener{
        void onItemClick(View view,int addrid);
        void onCheckClick(View view,int addrid,int status);
    }
    OnButtonClickListener onButtonClickListener;
    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener){
        this.onButtonClickListener=onButtonClickListener;
    }
}
