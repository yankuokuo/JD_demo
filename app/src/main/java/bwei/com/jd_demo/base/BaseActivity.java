package bwei.com.jd_demo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mumu on 2018/6/12.
 */

public abstract class BaseActivity<P extends BasePresenter> extends Fragment {


    protected  P  presenter;
    Unbinder unbinder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(provideLayoutId(), container, false);
        presenter =  providePresenter();
        initView(view);
        initListener();
        //提供presenter
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract P providePresenter();

    protected  abstract void initData();

    protected  void initListener(){}

    protected abstract void initView(View view);

    protected abstract int provideLayoutId();

    //解决内存泄漏

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }

//    @Override
//    protected void onDestroy() {
//        presenter.onDestroy();
//        super.onDestroy();
//    }
}
