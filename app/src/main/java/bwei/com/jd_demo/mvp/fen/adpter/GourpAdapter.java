package bwei.com.jd_demo.mvp.fen.adpter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwei.com.jd_demo.R;
import bwei.com.jd_demo.mvp.fen.model.bean.ChildBean;
import bwei.com.jd_demo.mvp.home.model.bean.LunBean;

public class GourpAdapter extends RecyclerView.Adapter<GourpAdapter.ViewHolder> {
    private  List<ChildBean.DataBean> list;
    private Context context;

    public GourpAdapter(List<ChildBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public GourpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fen_group_itme, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GourpAdapter.ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(list.get(i).getName());
        List<ChildBean.DataBean.ListBean> list1 = list.get(i).getList();
        ChildAdapter childAdapter = new ChildAdapter(list1, context);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
        viewHolder.recyclerView.setLayoutManager(gridLayoutManager);
        viewHolder.recyclerView.setAdapter(childAdapter);
        childAdapter.setOnItemClickListener(new ChildAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String name) {
                onItemClickListener.onItemClick(view,name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private final RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.fen_group_itme_text);
            recyclerView = itemView.findViewById(R.id.fen_group_itme_recyclerview);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view,String name);
    }
    OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
