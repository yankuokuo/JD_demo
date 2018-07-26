package bwei.com.jd_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.mvp.car.model.bean.AddBean;
import bwei.com.jd_demo.mvp.home.adpter.SearAdapter;
import bwei.com.jd_demo.mvp.home.model.bean.DetailBean;
import bwei.com.jd_demo.mvp.home.model.bean.JiuBean;
import bwei.com.jd_demo.mvp.home.model.bean.LunBean;
import bwei.com.jd_demo.mvp.home.model.bean.SearBean;
import bwei.com.jd_demo.mvp.home.persenter.HomePersenter;
import bwei.com.jd_demo.mvp.home.view.ILoginView;

public class SearchActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.sousuo_recyclerView)
    RecyclerView sousuoRecyclerView;
    @BindView(R.id.exit_login)
    ImageView exitLogin;
    @BindView(R.id.search_zonghe)
    TextView searchZonghe;
    @BindView(R.id.search_xiaoliang)
    TextView searchXiaoliang;
    @BindView(R.id.search_jiage)
    TextView searchJiage;
    @BindView(R.id.exit_sou)
    EditText exitSou;
    private HomePersenter homePersenter;
    private String info;
    private SearAdapter searAdapter;
    private String fenname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        info = intent.getStringExtra("info");
        fenname = intent.getStringExtra("fenname");
        Log.d("fenname", "111---" + fenname);
        homePersenter = new HomePersenter(this);
        homePersenter.SearLogin(info, "0");
        homePersenter.SearLogin(fenname, "0");
    }

    @Override
    public void onLoginSuccess(LunBean lunBean) {

    }

    @Override
    public void onjiuLoginSuccess(JiuBean jiuBean) {

    }

    @Override
    public void onsearLoginSucess(SearBean searBean) {
        List<SearBean.DataBean> list = searBean.getData();
        Log.d("搜索展示", "sssss" + list);
        if (list.equals("")) {
            Toast.makeText(SearchActivity.this, "没有搜索的结果", Toast.LENGTH_SHORT).show();
        }
        searAdapter = new SearAdapter(list, SearchActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        sousuoRecyclerView.setLayoutManager(gridLayoutManager);
        sousuoRecyclerView.setAdapter(searAdapter);
        searAdapter.setOnItemClickListener(new SearAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pid) {
                Intent intent = new Intent(SearchActivity.this, Detail2Activity.class);
                intent.putExtra("searpid", pid);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDetailLoginSucess(DetailBean detailBean) {

    }

    @Override
    public void addUsseccd(AddBean addBean) {

    }

    @Override
    public void onerror(String error) {

    }

    @OnClick({R.id.exit_login, R.id.search_zonghe, R.id.search_xiaoliang, R.id.search_jiage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exit_login:
                startActivity(new Intent(SearchActivity.this,ExitActivity.class));
                break;
            case R.id.search_zonghe:
                homePersenter.SearLogin(info, "0");
                homePersenter.SearLogin(fenname, "0");
                break;
            case R.id.search_xiaoliang:
                homePersenter.SearLogin(info, "1");
                homePersenter.SearLogin(fenname, "1");
                break;
            case R.id.search_jiage:
                homePersenter.SearLogin(info, "2");
                homePersenter.SearLogin(fenname, "2");
                break;
        }
    }

    @OnClick(R.id.exit_sou)
    public void onViewClicked() {
        startActivity(new Intent(SearchActivity.this,ExitActivity.class));
    }
}
