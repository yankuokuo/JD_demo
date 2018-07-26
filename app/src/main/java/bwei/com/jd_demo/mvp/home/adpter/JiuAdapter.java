package bwei.com.jd_demo.mvp.home.adpter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwei.com.jd_demo.R;
import bwei.com.jd_demo.mvp.home.model.bean.JiuBean;

public class JiuAdapter extends RecyclerView.Adapter<JiuAdapter.ViewHolder> {
    private List<JiuBean.DataBean> list;
    private Context context;

    public JiuAdapter(List<JiuBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public JiuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_jiu_itme, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JiuAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(list.get(i).getName());
        String icon = list.get(i).getIcon();
        Uri uri = Uri.parse(icon);
        viewHolder.imageView.setImageURI(uri);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cid = list.get(i).getCid();
                onItemClickListener.onItemClick(view,cid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView imageView;
        private final TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.home_jiu_item_image);
            textView = itemView.findViewById(R.id.home_jiu_item_name);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int cid);
    }
    OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
