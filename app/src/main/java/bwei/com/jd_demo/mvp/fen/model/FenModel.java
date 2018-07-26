package bwei.com.jd_demo.mvp.fen.model;
import bwei.com.jd_demo.mvp.fen.model.bean.ChildBean;
import bwei.com.jd_demo.mvp.fen.model.bean.ListBean;
import bwei.com.jd_demo.utils.RetrofitManager;
import io.reactivex.Observable;

public class FenModel {
    public Observable<ListBean> listlogin(){
        Observable<ListBean> listlogin=RetrofitManager.getDefault().create(IFenApi.class).listLogin();
        return  listlogin;
    }
    public Observable<ChildBean> childlogin(int cid){
        Observable<ChildBean> childlogin=RetrofitManager.getDefault().create(IFenApi.class).childLogin("https://www.zhaoapi.cn/product/getProductCatagory"+"?cid="+cid);
        return  childlogin;
    }
}
