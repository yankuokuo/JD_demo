package bwei.com.jd_demo.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mumu on 2018/6/12.
 */

public abstract class BasePresenter<V extends IView> {
    protected V view;

    public BasePresenter(V view) {
        this.view = view;
        initModel();
    }

    //初始化model
    protected abstract void initModel();

    //内存泄漏的解决
    void onDestroy() {
        view = null;
    }

}
