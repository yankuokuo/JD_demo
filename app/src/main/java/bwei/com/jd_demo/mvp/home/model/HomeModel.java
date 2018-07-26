package bwei.com.jd_demo.mvp.home.model;

import bwei.com.jd_demo.mvp.car.model.ICarApi;
import bwei.com.jd_demo.mvp.car.model.bean.AddBean;
import bwei.com.jd_demo.mvp.home.model.bean.DetailBean;
import bwei.com.jd_demo.mvp.home.model.bean.JiuBean;
import bwei.com.jd_demo.mvp.home.model.bean.LunBean;
import bwei.com.jd_demo.mvp.home.model.bean.SearBean;
import bwei.com.jd_demo.utils.RetrofitManager;
import io.reactivex.Observable;

public class HomeModel {
    public Observable<LunBean> login() {
        Observable<LunBean> login = RetrofitManager.getDefault().create(IHomeApi.class).login();
        return login;
    }
    public Observable<JiuBean>jiulogin(){
        Observable<JiuBean>jiulogin=RetrofitManager.getDefault().create(IHomeApi.class).jiulogin();
        return jiulogin;
    }
    public Observable<SearBean>searlogin(String keywords,String sort){
        Observable<SearBean>searLogin=RetrofitManager.getDefault().create(IHomeApi.class).searlogin(keywords,sort);
        return searLogin;
    }
    public Observable<DetailBean>detaillogin(Integer pid){
        Observable<DetailBean>detailLogin=RetrofitManager.getDefault().create(IHomeApi.class).detailogin(pid);
        return detailLogin;
    }
    public Observable<AddBean>addmodel(Integer uid, Integer pid){
        return RetrofitManager.getDefault().create(IHomeApi.class).addlogin(uid,pid);
    }
}
