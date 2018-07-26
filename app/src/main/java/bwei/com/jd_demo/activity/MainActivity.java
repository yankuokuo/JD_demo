package bwei.com.jd_demo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwei.com.jd_demo.R;
import bwei.com.jd_demo.mvp.car.view.CarFragment;
import bwei.com.jd_demo.mvp.fa.view.Fafrengment;
import bwei.com.jd_demo.mvp.fen.view.FenFragment;
import bwei.com.jd_demo.mvp.home.view.HomeFragment;
import bwei.com.jd_demo.mvp.my.view.MyFragment;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.main_framenlayout)
    FrameLayout mainFramenlayout;
    @BindView(R.id.main_bottombar)
    BottomBar mainBottombar;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.replace(R.id.main_framenlayout,new HomeFragment());
//        transaction.commit();
        mainBottombar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId){
                    case R.id.tab_home:
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.main_framenlayout,new HomeFragment());
                        transaction.commit();
                        break;
                    case R.id.tab_fen:
                        FragmentManager fm1 = getSupportFragmentManager();
                        FragmentTransaction transaction1 = fm1.beginTransaction();
                        transaction1.replace(R.id.main_framenlayout,new FenFragment());
                        transaction1.commit();
                        break;
                    case R.id.tab_fa:
                        FragmentManager fm5 = getSupportFragmentManager();
                        FragmentTransaction transaction5 = fm5.beginTransaction();
                        transaction5.replace(R.id.main_framenlayout,new Fafrengment());
                        transaction5.commit();
                        break;
                    case R.id.tab_car:
                        FragmentManager fm2 = getSupportFragmentManager();
                        FragmentTransaction transaction2 = fm2.beginTransaction();
                        transaction2.replace(R.id.main_framenlayout,new CarFragment());
                        transaction2.commit();
                        break;
                    case R.id.tab_my:
                        FragmentManager fm3 = getSupportFragmentManager();
                        FragmentTransaction transaction3 = fm3.beginTransaction();
                        transaction3.replace(R.id.main_framenlayout,new MyFragment());
                        transaction3.commit();
                        break;
                }
            }
        });

    }
}
