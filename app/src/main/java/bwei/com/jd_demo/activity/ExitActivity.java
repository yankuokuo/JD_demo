package bwei.com.jd_demo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.layout.FlowLayout;
import bwei.com.jd_demo.mvp.home.model.bean.JiuBean;
import bwei.com.jd_demo.mvp.home.model.bean.LunBean;
import bwei.com.jd_demo.mvp.home.model.bean.SearBean;
import bwei.com.jd_demo.mvp.home.persenter.HomePersenter;
import bwei.com.jd_demo.mvp.home.view.ILoginView;

public class ExitActivity extends AppCompatActivity{

    @BindView(R.id.exit_sou)
    EditText exitSou;
    @BindView(R.id.exit_login)
    ImageView exitLogin;
    @BindView(R.id.exit_flowlayout)
    FlowLayout exitFlowlayout;
    @BindView(R.id.exit_flow2)
    FlowLayout exitFlow2;
    private TextView textView;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        list.add("修身休闲裤");
        list.add("笔记本");
        list.add("哈伦裤");
        list.add("破洞牛仔");
        list.add("卫衣");
        list.add("连衣裙");
        list.add("月饼");
        list.add("糖巧");
        list.add("休闲零食");
        for (int i = 0; i < list.size(); i++) {
            textView = new TextView(getApplicationContext());
            textView.setText(list.get(i));
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(30);
            textView.setPadding(10, 10, 10, 10);
            exitFlowlayout.addView(textView);
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    exitSou.setText(list.get(finalI));
                }
            });
        }
    }
    @OnClick({R.id.exit_sou, R.id.exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exit_sou:
                break;
            case R.id.exit_login:
                String s = exitSou.getText().toString();
                SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString("qq",s);
                edit.commit();
                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                String name = sharedPreferences.getString("qq", "");
                textView = new TextView(getApplicationContext());
                textView.setText(name);
                textView.setTextColor(Color.BLACK);
                textView.setTextSize(30);
                textView.setPadding(10, 10, 10, 10);
                exitFlow2.addView(textView);
                Intent intent = new Intent(ExitActivity.this, SearchActivity.class);
                intent.putExtra("info",s);
                startActivity(intent);
                break;
        }
    }
}
