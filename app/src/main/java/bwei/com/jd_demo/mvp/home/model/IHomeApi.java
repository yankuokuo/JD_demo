package bwei.com.jd_demo.mvp.home.model;

import bwei.com.jd_demo.mvp.car.model.bean.AddBean;
import bwei.com.jd_demo.mvp.home.model.bean.DetailBean;
import bwei.com.jd_demo.mvp.home.model.bean.JiuBean;
import bwei.com.jd_demo.mvp.home.model.bean.LunBean;
import bwei.com.jd_demo.mvp.home.model.bean.SearBean;
import bwei.com.jd_demo.utils.Apis;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IHomeApi {
    @GET("ad/getAd")
    Observable<LunBean> login();
    @GET("product/getCatagory")
    Observable<JiuBean>jiulogin();
    @GET("product/searchProducts")
    Observable<SearBean>searlogin(@Query("keywords")String keywords,@Query("sort")String sort);
    @GET("product/getProductDetail")
    Observable<DetailBean>detailogin(@Query("pid")Integer pid);
    @GET("product/addCart")
    Observable<AddBean>addlogin(@Query("Uid")Integer Uid, @Query("Pid")Integer pid);
}
