package bwei.com.jd_demo.mvp.car.model;

import bwei.com.jd_demo.mvp.car.model.bean.AddBean;
import bwei.com.jd_demo.mvp.car.model.bean.CarBean;
import bwei.com.jd_demo.mvp.car.model.bean.CreateBean;
import bwei.com.jd_demo.mvp.car.model.bean.DeleteBean;
import bwei.com.jd_demo.mvp.car.model.bean.UpdataCartBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICarApi {
    @GET("product/getCarts")
    Observable<CarBean>CarLogin(@Query("Uid")Integer uid);

    @GET("product/deleteCart")
    Observable<DeleteBean>deletelogin(@Query("Uid")Integer Uid, @Query("Pid")Integer pid);
    @GET("product/updateCarts")
    Observable<UpdataCartBean>updateLogin(@Query("uid")Integer uid,@Query("sellerid") String sellerid,@Query("pid")Integer pid,@Query("num")Integer num,@Query("selected")Integer selected);
    @GET("product/createOrder")
    Observable<CreateBean>createlogin(@Query("Uid")Integer uid,@Query("price")float price);
}
