package bwei.com.jd_demo.mvp.my.persenter;

import bwei.com.jd_demo.base.BasePresenter;
import bwei.com.jd_demo.mvp.my.model.MyModel;
import bwei.com.jd_demo.mvp.my.model.bean.LoginBean;
import bwei.com.jd_demo.mvp.my.model.bean.NickBean;
import bwei.com.jd_demo.mvp.my.model.bean.OrdersBean;
import bwei.com.jd_demo.mvp.my.model.bean.RegBean;
import bwei.com.jd_demo.mvp.my.model.bean.UpOrderBean;
import bwei.com.jd_demo.mvp.my.model.bean.UploadBean;
import bwei.com.jd_demo.mvp.my.model.bean.UserInfoBean;
import bwei.com.jd_demo.mvp.my.view.MyView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class MyPersenter extends BasePresenter<MyView> {

    private MyModel myModel;

    public MyPersenter(MyView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        myModel = new MyModel();
    }
    public void login(String mobile,String password){
        if(mobile.equals("")){
            if (view!=null){
                view.onLoginFaild("手机号不能为空！！！");
            }
            return;
        }else {
            myModel.loginb(mobile, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<LoginBean>() {
                        @Override
                        public void accept(LoginBean loginBean) throws Exception {
                            view.OnDengLoginUsseccd(loginBean);
                        }
                    });

        }
    }
    public void UploadLogin(Integer uid, MultipartBody.Part part){
        myModel.upload(uid,part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UploadBean>() {
                    @Override
                    public void accept(UploadBean uploadBean) throws Exception {
                        view.OnUploadUsseccd(uploadBean);
                    }
                });
    }
    public void RegLogin(String mobile,String password){
        myModel.reglogin(mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegBean>() {
                    @Override
                    public void accept(RegBean regBean) throws Exception {
                        view.OnRegLoginUsseccd(regBean);
                    }
                });
    }
    public void UserLogin(Integer uid){
        myModel.userlogin(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserInfoBean>() {
                    @Override
                    public void accept(UserInfoBean userInfoBean) throws Exception {
                        view.OngetUserInfo(userInfoBean);
                    }
                });
    }
    public void NickLogin(Integer uid,String nickname){
        myModel.nickLogin(uid,nickname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NickBean>() {
                    @Override
                    public void accept(NickBean nickBean) throws Exception {
                        view.OnNickname(nickBean);
                    }
                });
    }
    public void Orders(Integer uid){
        myModel.OrdersLogin(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OrdersBean>() {
                    @Override
                    public void accept(OrdersBean ordersBean) throws Exception {
                        view.OnOrdersLogin(ordersBean);
                    }
                });
    }
    public void UpOrderLogin(Integer uid,String status,Integer orderid){
        myModel.uporderLogin(uid,status,orderid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpOrderBean>() {
                    @Override
                    public void accept(UpOrderBean upOrderBean) throws Exception {
                        view.OnUpOrderUsseccd(upOrderBean);
                    }
                });
    }
}
