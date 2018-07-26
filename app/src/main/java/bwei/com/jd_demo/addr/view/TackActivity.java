package bwei.com.jd_demo.addr.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.addr.model.adapter.Addrdapter;
import bwei.com.jd_demo.addr.model.bean.AddAddBean;
import bwei.com.jd_demo.addr.model.bean.GetAddBean;
import bwei.com.jd_demo.addr.model.bean.GetDefaultBean;
import bwei.com.jd_demo.addr.model.bean.SetAddBean;
import bwei.com.jd_demo.addr.model.bean.UpdateAddBean;
import bwei.com.jd_demo.addr.persenter.AddrPersenter;

public class TackActivity extends AppCompatActivity implements AddrView {

    @BindView(R.id.tack_back)
    TextView tackBack;
    @BindView(R.id.tack_recyclerview)
    RecyclerView tackRecyclerview;
    @BindView(R.id.tack_add)
    Button tackAdd;
//    @BindView(R.id.addr_moren_name)
//    TextView addrMorenName;
//    @BindView(R.id.addr_moren_mobile)
//    TextView addrMorenMobile;
//    @BindView(R.id.addr_moren_addr)
//    TextView addrMorenAddr;
    private AddrPersenter addrPersenter;
    private int uid;
    private TextView addr;
    private View inflate;
    private TextView name;
    private TextView mobile;
    private Addrdapter addrdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tack);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        uid = intent.getIntExtra("uid", 0);
        addrPersenter = new AddrPersenter(this);
        addrPersenter.GetAddrLogin(uid);
        //addrPersenter.GetDefaultLogin(uid);
    }


    @OnClick({R.id.tack_back, R.id.tack_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tack_back:
                finish();
                break;
            case R.id.tack_add:
                Intent intent = new Intent(TackActivity.this, AddActivity.class);
                intent.putExtra("adduid", uid);
                startActivityForResult(intent,8);
                break;
        }
    }

    @Override
    public void OnAddAddUssed(AddAddBean addAddBean) {

    }

    @Override
    public void OnGetAddUssed(GetAddBean getAddBean) {
        List<GetAddBean.DataBean> list = getAddBean.getData();
        addrdapter = new Addrdapter(list, TackActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        tackRecyclerview.setLayoutManager(linearLayoutManager);
        tackRecyclerview.setAdapter(addrdapter);
        addrdapter.notifyDataSetChanged();
        addrdapter.setOnButtonClickListener(new Addrdapter.OnButtonClickListener() {
            @Override
            public void onItemClick(View view, final int addrid) {
                inflate = View.inflate(TackActivity.this, R.layout.updateadd_dialog, null);
                addr = inflate.findViewById(R.id.addr_update_addr);
                name = inflate.findViewById(R.id.addr_update_name);
                mobile = inflate.findViewById(R.id.addr_update_mobile);
                AlertDialog.Builder builder = new AlertDialog.Builder(TackActivity.this);
                builder.setTitle("编辑收货地址");
                builder.setView(inflate);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String addr = TackActivity.this.addr.getText().toString();
                        String name = TackActivity.this.name.getText().toString();
                        String mobile = TackActivity.this.mobile.getText().toString();
                        Log.e("addr", "+++++" + addr + name + mobile);
                        addrPersenter.UpdateAddrLogin(uid, addrid, addr, name, mobile);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
            }

            @Override
            public void onCheckClick(View view, int addrid, int status) {
                addrPersenter.SetAddrLogin(uid, addrid, 1);
                addrPersenter.GetAddrLogin(uid);
                addrdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void OnGetDefaultUssed(GetDefaultBean getDefaultBean) {
//        if (getDefaultBean.getCode().equals("0")){
//            GetDefaultBean.DataBean data = getDefaultBean.getData();
//            String name = data.getName();
//            int mobile = data.getMobile();
//            String addr = data.getAddr();
//            addrMorenName.setText(name);
//            addrMorenMobile.setText(mobile+"");
//            addrMorenAddr.setText(addr);
//        }

    }

    @Override
    public void OnSetAddUssed(SetAddBean setAddBean) {
        if (setAddBean.getCode().equals("")) {
            Toast.makeText(TackActivity.this, "设置默认地址", Toast.LENGTH_SHORT).show();
            addrPersenter.GetAddrLogin(uid);
            addrdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void OnUpdateAddUssed(UpdateAddBean updateAddBean) {
        if (updateAddBean.getCode().equals("0")) {
            Toast.makeText(TackActivity.this, "修改地址成功", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(TackActivity.this, "修改地址失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnError(String error) {
        Toast.makeText(TackActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==8&&resultCode==8){
            addrPersenter.GetAddrLogin(uid);
            addrdapter.notifyDataSetChanged();
        }
    }
}
