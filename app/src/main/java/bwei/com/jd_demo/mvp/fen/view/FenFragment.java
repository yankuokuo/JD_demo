package bwei.com.jd_demo.mvp.fen.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.common.logging.LoggingDelegate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.activity.SearchActivity;
import bwei.com.jd_demo.base.BaseActivity;
import bwei.com.jd_demo.mvp.fen.adpter.GourpAdapter;
import bwei.com.jd_demo.mvp.fen.adpter.ListAdapter;
import bwei.com.jd_demo.mvp.fen.model.bean.ChildBean;
import bwei.com.jd_demo.mvp.fen.model.bean.ListBean;
import bwei.com.jd_demo.mvp.fen.persenter.FenPersenter;

public class FenFragment extends BaseActivity<FenPersenter> implements FenView {
    @BindView(R.id.fen_list)
    ListView fenList;
    Unbinder unbinder;
    @BindView(R.id.fen_recylerview_gourp)
    RecyclerView fenRecylerviewGourp;
    Unbinder unbinder1;
    private List<ListBean.DataBean> list;

    @Override
    protected FenPersenter providePresenter() {
        final FenPersenter fenPersenter = new FenPersenter(this);
        fenPersenter.fenLogin();
        fenPersenter.childLogin(1);
        return fenPersenter;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fen_fregment;
    }

    @Override
    public void onListgetUsseccd(ListBean listBean) {
        list = listBean.getData();
        final ListAdapter listAdapter = new ListAdapter(list, getContext());
        fenList.setAdapter(listAdapter);
        fenList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = view.findViewById(R.id.fen_list_itme_text);
                Toast.makeText(getContext(), tv.getText(), Toast.LENGTH_SHORT).show();
                int cid = list.get(i).getCid();
                Log.d("vvvv", "cid------" + cid);
                presenter.childLogin(cid);
            }
        });
    }

    @Override
    public void onchildgetUseeccd(ChildBean childBean) {
        List<ChildBean.DataBean> data = childBean.getData();
        GourpAdapter gourpAdapter = new GourpAdapter(data, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        fenRecylerviewGourp.setLayoutManager(linearLayoutManager);
        fenRecylerviewGourp.setAdapter(gourpAdapter);
        gourpAdapter.setOnItemClickListener(new GourpAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String name) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("fenname",name);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onerror(String error) {
        Log.d("sss","sss");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
