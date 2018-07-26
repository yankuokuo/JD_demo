package bwei.com.jd_demo.mvp.my.model;

import bwei.com.jd_demo.activity.LoginActivity;
import bwei.com.jd_demo.mvp.my.model.bean.LoginBean;
import bwei.com.jd_demo.mvp.my.model.bean.NickBean;
import bwei.com.jd_demo.mvp.my.model.bean.OrdersBean;
import bwei.com.jd_demo.mvp.my.model.bean.RegBean;
import bwei.com.jd_demo.mvp.my.model.bean.UpOrderBean;
import bwei.com.jd_demo.mvp.my.model.bean.UploadBean;
import bwei.com.jd_demo.mvp.my.model.bean.UserInfoBean;
import bwei.com.jd_demo.utils.RetrofitManager;
import io.reactivex.Observable;
import okhttp3.MultipartBody;

public class MyModel {
    public Observable<LoginBean> loginb(String mobile,String password){
    return RetrofitManager.getDefault().create(MyApi.class).denglogin(mobile,password);
    }
    public Observable<UploadBean>upload(Integer uid, MultipartBody.Part part){
        return RetrofitManager.getDefault().create(MyApi.class).upLoadPhoto(uid,part);
    };
    public Observable<RegBean>reglogin(String mobile,String password){
        return RetrofitManager.getDefault().create(MyApi.class).reglogin(mobile,password);
    }
    public Observable<UserInfoBean>userlogin(Integer uid){
        return RetrofitManager.getDefault().create(MyApi.class).Userlogin(uid);
    }
    public Observable<NickBean>nickLogin(Integer uid,String nickname){
        return RetrofitManager.getDefault().create(MyApi.class).nicklogin(uid,nickname);
    }
    public Observable<OrdersBean>OrdersLogin(Integer uid){
        return RetrofitManager.getDefault().create(MyApi.class).OrdersLogin(uid);
    }
    public Observable<UpOrderBean>uporderLogin(Integer uid,String status,Integer orderid){
        return RetrofitManager.getDefault().create(MyApi.class).uproderLogin(uid,status,orderid);
    }
}
