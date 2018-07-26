package bwei.com.jd_demo.addr.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.addr.model.bean.AddAddBean;
import bwei.com.jd_demo.addr.model.bean.GetAddBean;
import bwei.com.jd_demo.addr.model.bean.GetDefaultBean;
import bwei.com.jd_demo.addr.model.bean.SetAddBean;
import bwei.com.jd_demo.addr.model.bean.UpdateAddBean;
import bwei.com.jd_demo.addr.persenter.AddrPersenter;

public class AddActivity extends AppCompatActivity implements AddrView {

    @BindView(R.id.addr_add_back)
    TextView addrAddBack;
    @BindView(R.id.addr_add_name)
    EditText addrAddName;
    @BindView(R.id.addr_add_mobile)
    EditText addrAddMobile;
    @BindView(R.id.addr_add_addr)
    EditText addrAddAddr;
    @BindView(R.id.addr_add_login)
    Button addrAddLogin;
    private AddrPersenter addrPersenter;
    private int adduid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        adduid = intent.getIntExtra("adduid", 0);
        addrPersenter = new AddrPersenter(this);

    }

    @OnClick({R.id.addr_add_back, R.id.addr_add_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addr_add_back:
                finish();
                break;
            case R.id.addr_add_login:
                String name = addrAddName.getText().toString();
                String mobile = addrAddMobile.getText().toString();
                String addr = addrAddAddr.getText().toString();
                addrPersenter.AddAddrLogin(adduid,addr,mobile,name);
                break;
        }
    }
    @Override
    public void OnAddAddUssed(AddAddBean addAddBean) {
        if (addAddBean.getCode().equals("0")){
            Toast.makeText(AddActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            setResult(8,intent);
            finish();
        }else{
            Toast.makeText(AddActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnGetAddUssed(GetAddBean getAddBean) {

    }

    @Override
    public void OnGetDefaultUssed(GetDefaultBean getDefaultBean) {

    }

    @Override
    public void OnSetAddUssed(SetAddBean setAddBean) {

    }

    @Override
    public void OnUpdateAddUssed(UpdateAddBean updateAddBean) {

    }

    @Override
    public void OnError(String error) {

    }
}
