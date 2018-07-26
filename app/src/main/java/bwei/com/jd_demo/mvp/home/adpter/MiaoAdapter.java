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
import bwei.com.jd_demo.mvp.home.model.bean.LunBean;

public class MiaoAdapter extends RecyclerView.Adapter<MiaoAdapter.ViewHolder> {
    private  List<LunBean.MiaoshaBean.ListBeanX> list;
    private Context context;

    public MiaoAdapter(List<LunBean.MiaoshaBean.ListBeanX> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MiaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hom_miao_itme, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MiaoAdapter.ViewHolder viewHolder,final int i) {

        String[] split = list.get(i).getImages().split("\\|");
        Uri uri = Uri.parse(split[0]);
        viewHolder.imageView.setImageURI(uri);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pid = list.get(i).getPid();
                onItemClickListener.onItemClick(view,pid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.home_miao_itme_image);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int pid);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
