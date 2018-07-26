package bwei.com.jd_demo.mvp.my.model;

import bwei.com.jd_demo.mvp.my.model.bean.LoginBean;
import bwei.com.jd_demo.mvp.my.model.bean.NickBean;
import bwei.com.jd_demo.mvp.my.model.bean.OrdersBean;
import bwei.com.jd_demo.mvp.my.model.bean.RegBean;
import bwei.com.jd_demo.mvp.my.model.bean.UpOrderBean;
import bwei.com.jd_demo.mvp.my.model.bean.UploadBean;
import bwei.com.jd_demo.mvp.my.model.bean.UserInfoBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface MyApi {

    @GET("user/login")
    Observable<LoginBean>denglogin(@Query("mobile")String mobile,@Query("password")String password);
    @GET("user/reg")
    Observable<RegBean>reglogin(@Query("mobile")String mobile, @Query("password")String password);
    @Multipart
    @POST("file/upload")
    Observable<UploadBean> upLoadPhoto(@Query("uid") Integer uid, @Part MultipartBody.Part part);
    @GET("user/getUserInfo")
    Observable<UserInfoBean>Userlogin(@Query("uid")Integer uid);
    @GET("user/updateNickName")
    Observable<NickBean>nicklogin(@Query("uid")Integer uid,@Query("nickname")String nickname);
    @GET("product/getOrders")
    Observable<OrdersBean>OrdersLogin(@Query("uid")Integer uid);
    @GET("product/updateOrder")
    Observable<UpOrderBean>uproderLogin(@Query("Uid")Integer uid,@Query("status")String status,@Query("orderId")Integer orderid);
}
