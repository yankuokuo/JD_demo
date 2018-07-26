package bwei.com.jd_demo.mvp.home.adpter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwei.com.jd_demo.R;
import bwei.com.jd_demo.mvp.home.model.bean.LunBean;
import bwei.com.jd_demo.mvp.home.model.bean.SearBean;
import retrofit2.http.POST;

public class SearAdapter extends RecyclerView.Adapter<SearAdapter.ViewHolder> {
    private  List<SearBean.DataBean> list;
    private Context context;

    private static final String TAG = "SearAdapter*******";
    public SearAdapter(List<SearBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public SearAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_sousuo_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearAdapter.ViewHolder viewHolder,final int i) {
        viewHolder.name.setText(list.get(i).getTitle());
        Log.e(TAG, "onBindViewHolder: -------------" +list.get(i).getTitle());
        viewHolder.price.setText("$"+list.get(i).getPrice());
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
        private final TextView name;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.sousuo_image);
            name = itemView.findViewById(R.id.sousuo_name);
            price = itemView.findViewById(R.id.sousuo_price);
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
