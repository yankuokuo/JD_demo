package bwei.com.jd_demo.mvp.fen.adpter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwei.com.jd_demo.R;
import bwei.com.jd_demo.mvp.fen.model.bean.ChildBean;
import bwei.com.jd_demo.mvp.home.adpter.JiuAdapter;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {
    private  List<ChildBean.DataBean.ListBean> list;
    private Context context;

    public ChildAdapter(List<ChildBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fen_child_itme, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChildAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(list.get(i).getName());
        String[] split = list.get(i).getIcon().split("\\|");
        Uri uri = Uri.parse(split[0]);
        viewHolder.imageView.setImageURI(uri);
       viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String name = list.get(i).getName();
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
        private final SimpleDraweeView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.fen_child_itme_text);
            imageView = itemView.findViewById(R.id.fen_child_itme_image);
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
