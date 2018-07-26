package bwei.com.jd_demo.mvp.car.model;

import bwei.com.jd_demo.mvp.car.model.bean.AddBean;
import bwei.com.jd_demo.mvp.car.model.bean.CarBean;
import bwei.com.jd_demo.mvp.car.model.bean.CreateBean;
import bwei.com.jd_demo.mvp.car.model.bean.DeleteBean;
import bwei.com.jd_demo.mvp.car.model.bean.UpdataCartBean;
import bwei.com.jd_demo.utils.RetrofitManager;
import io.reactivex.Observable;

public class CarModel{
  public   Observable<CarBean> carmodel(Integer uid){
        Observable<CarBean>carModel= RetrofitManager.getDefault().create(ICarApi.class).CarLogin(uid);
        return carModel;
    }

    public Observable<DeleteBean>deletemodel(Integer uid, Integer pid){
        return RetrofitManager.getDefault().create(ICarApi.class).deletelogin(uid,pid);
    }
    public Observable<UpdataCartBean>updateLogin(Integer uid,String sellerid,Integer pid,Integer num,Integer selected){
        return RetrofitManager.getDefault().create(ICarApi.class).updateLogin(uid,sellerid,pid,num,selected);
    }
    public Observable<CreateBean>CreateLogin(Integer uid,float price){
      return RetrofitManager.getDefault().create(ICarApi.class).createlogin(uid,price);
    }
}
