package bwei.com.jd_demo.mvp.home.persenter;

import android.util.Log;

import java.util.List;

import bwei.com.jd_demo.base.BasePresenter;
import bwei.com.jd_demo.mvp.car.model.bean.AddBean;
import bwei.com.jd_demo.mvp.home.model.HomeModel;
import bwei.com.jd_demo.mvp.home.model.bean.DetailBean;
import bwei.com.jd_demo.mvp.home.model.bean.JiuBean;
import bwei.com.jd_demo.mvp.home.model.bean.LunBean;
import bwei.com.jd_demo.mvp.home.model.bean.SearBean;
import bwei.com.jd_demo.mvp.home.view.ILoginView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePersenter extends BasePresenter<ILoginView> {
    private  HomeModel homeModel;

    public HomePersenter(ILoginView iLoginView) {
        super(iLoginView);
    }

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    public void login1(){
        homeModel.login()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LunBean>() {
                    @Override
                    public void accept(LunBean lunBean) throws Exception {
                        view.onLoginSuccess(lunBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view!=null){
                            view.onerror("不能为空");
                        }
                    }
                });
        homeModel.jiulogin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<JiuBean>() {
                    @Override
                    public void accept(JiuBean jiuBean) throws Exception {
                        view.onjiuLoginSuccess(jiuBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view!=null){
                            view.onerror("不能为空");
                        }
                    }
                });
    }
    public void SearLogin(String keywords,String sort){
        homeModel.searlogin(keywords,sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearBean>() {
                    @Override
                    public void accept(SearBean searBean) throws Exception {
                        view.onsearLoginSucess(searBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view!=null){
                            view.onerror("不能为空");
                        }
                    }
                });
    }
    public void detailLogin(Integer pid){
        homeModel.detaillogin(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DetailBean>() {
                    @Override
                    public void accept(DetailBean detailBean) throws Exception {
                        view.onDetailLoginSucess(detailBean);
                    }
                });
    }
    public void addLogin(Integer uid,Integer pid){
        homeModel.addmodel(uid,pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddBean>() {
                    @Override
                    public void accept(AddBean addBean) throws Exception {
                        view.addUsseccd(addBean);
                    }
                });
    }
}
