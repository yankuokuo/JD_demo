package bwei.com.jd_demo.mvp.my.view;

import bwei.com.jd_demo.base.IView;
import bwei.com.jd_demo.mvp.my.model.bean.LoginBean;
import bwei.com.jd_demo.mvp.my.model.bean.NickBean;
import bwei.com.jd_demo.mvp.my.model.bean.OrdersBean;
import bwei.com.jd_demo.mvp.my.model.bean.RegBean;
import bwei.com.jd_demo.mvp.my.model.bean.UpOrderBean;
import bwei.com.jd_demo.mvp.my.model.bean.UploadBean;
import bwei.com.jd_demo.mvp.my.model.bean.UserInfoBean;

public interface MyView extends IView {
    void OnDengLoginUsseccd(LoginBean loginBean);
    void OnRegLoginUsseccd(RegBean regBean);
    void OnUploadUsseccd(UploadBean uploadBean);
    void OngetUserInfo(UserInfoBean userInfoBean);
    void OnNickname(NickBean nickBean);
    void OnOrdersLogin(OrdersBean ordersBean);
    void onLoginFaild(String error);
    void OnUpOrderUsseccd(UpOrderBean upOrderBean);
}
