package bwei.com.jd_demo.mvp.fen.adpter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.mvp.fen.model.bean.ListBean;

public class ListAdapter extends BaseAdapter {
    private List<ListBean.DataBean> list;
    private Context context;

    public ListAdapter(List<ListBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(context, R.layout.fen_listview_itme, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();

        }
        viewHolder.fenListItmeText.setText(list.get(i).getName());
        Log.i("zzzz",list.get(i).getName());
        return view;
    }

    public class ViewHolder {
        @BindView(R.id.fen_list_itme_text)
        TextView fenListItmeText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
