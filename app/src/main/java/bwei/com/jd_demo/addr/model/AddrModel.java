package bwei.com.jd_demo.addr.model;

import bwei.com.jd_demo.addr.model.bean.AddAddBean;
import bwei.com.jd_demo.addr.model.bean.GetAddBean;
import bwei.com.jd_demo.addr.model.bean.GetDefaultBean;
import bwei.com.jd_demo.addr.model.bean.SetAddBean;
import bwei.com.jd_demo.addr.model.bean.UpdateAddBean;
import bwei.com.jd_demo.utils.RetrofitManager;
import io.reactivex.Observable;

public class AddrModel {
    public Observable<GetAddBean>GetAddLogin(Integer uid){
        return RetrofitManager.getDefault().create(AddrApi.class).GetAddLogin(uid);
    }
    public Observable<AddAddBean>AddAddLogin(Integer uid,String addr,String mobile,String name){
        return RetrofitManager.getDefault().create(AddrApi.class).AddAddLogin(uid,addr,mobile,name);
    }
    public Observable<UpdateAddBean>UpdateAddLogin(Integer uid,Integer addrid,String addr,String name,String mobile){
        return RetrofitManager.getDefault().create(AddrApi.class).UpdateAddLogin(uid,addrid,addr,name,mobile);
    }
    public Observable<SetAddBean>SetAddLogin(Integer uid,Integer addrid,Integer status){
        return RetrofitManager.getDefault().create(AddrApi.class).SetAddLogin(uid,addrid,status);
    }
    public Observable<GetDefaultBean>GetDefaultLogin(Integer uid){
        return RetrofitManager.getDefault().create(AddrApi.class).GetDefaultLogin(uid);
    }
}
