package bwei.com.jd_demo.mvp.fen.model;

import bwei.com.jd_demo.mvp.fen.model.bean.ChildBean;
import bwei.com.jd_demo.mvp.fen.model.bean.ListBean;
import bwei.com.jd_demo.mvp.fen.view.FenFragment;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IFenApi {
    @GET("product/getCatagory")
    Observable<ListBean> listLogin();

    @GET()
    Observable<ChildBean> childLogin(@Url String url);
}
