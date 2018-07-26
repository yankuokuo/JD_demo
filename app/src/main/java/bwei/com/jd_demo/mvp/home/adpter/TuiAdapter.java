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
import bwei.com.jd_demo.mvp.home.model.bean.LunBean;

public class TuiAdapter extends RecyclerView.Adapter<TuiAdapter.ViewHolder> {
    private  List<LunBean.TuijianBean.ListBean> list1;
    private Context context;

    public TuiAdapter(List<LunBean.TuijianBean.ListBean> list1, Context context) {
        this.list1 = list1;
        this.context = context;
    }

    @Override
    public TuiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_tuijian_itme, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TuiAdapter.ViewHolder viewHolder, final int i) {

        String[] split = list1.get(i).getImages().split("\\|");
        Uri uri = Uri.parse(split[0]);
        viewHolder.imageView.setImageURI(uri);
        viewHolder.name.setText(list1.get(i).getTitle());
        viewHolder.price.setText(list1.get(i).getPrice());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pid = list1.get(i).getPid();
                onItemClickListener.onItemClick(view,pid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView imageView;
        private final TextView name;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.home_tuijian_image);
            name = itemView.findViewById(R.id.home_tuijian_name);
            price = itemView.findViewById(R.id.home_tuijian_perce);
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
