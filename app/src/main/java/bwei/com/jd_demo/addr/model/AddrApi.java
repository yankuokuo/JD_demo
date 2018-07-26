package bwei.com.jd_demo.addr.model;

import bwei.com.jd_demo.addr.model.bean.AddAddBean;
import bwei.com.jd_demo.addr.model.bean.GetAddBean;
import bwei.com.jd_demo.addr.model.bean.GetDefaultBean;
import bwei.com.jd_demo.addr.model.bean.SetAddBean;
import bwei.com.jd_demo.addr.model.bean.UpdateAddBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AddrApi {
    @GET("user/getAddrs")
    Observable<GetAddBean>GetAddLogin(@Query("Uid")Integer uid);
    @GET("user/addAddr")
    Observable<AddAddBean>AddAddLogin(@Query("Uid")Integer uid,@Query("addr")String addr,@Query("mobile")String mobile,@Query("name")String name);
    @GET("user/updateAddr")
    Observable<UpdateAddBean>UpdateAddLogin(@Query("Uid")Integer uid,@Query("addrid")Integer addrid,@Query("addr")String addr,@Query("name")String name,@Query("mobile")String mobile);
    @GET("user/setAddr")
    Observable<SetAddBean>SetAddLogin(@Query("Uid")Integer uid,@Query("addrid")Integer addrid,@Query("status")Integer status);
    @GET("user/getDefaultAddr")
    Observable<GetDefaultBean>GetDefaultLogin(@Query("Uid")Integer uid);
}
