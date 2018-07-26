package bwei.com.jd_demo.addr.persenter;

import bwei.com.jd_demo.addr.model.AddrModel;
import bwei.com.jd_demo.addr.model.bean.AddAddBean;
import bwei.com.jd_demo.addr.model.bean.GetAddBean;
import bwei.com.jd_demo.addr.model.bean.GetDefaultBean;
import bwei.com.jd_demo.addr.model.bean.SetAddBean;
import bwei.com.jd_demo.addr.model.bean.UpdateAddBean;
import bwei.com.jd_demo.addr.view.AddrView;
import bwei.com.jd_demo.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AddrPersenter extends BasePresenter<AddrView>{

    private AddrModel addrModel;

    public AddrPersenter(AddrView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        addrModel = new AddrModel();
    }
    public void GetAddrLogin(final Integer uid){
        addrModel.GetAddLogin(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetAddBean>() {
                    @Override
                    public void accept(GetAddBean getAddBean) throws Exception {
                        view.OnGetAddUssed(getAddBean);
                    }
                });
    }
    public void AddAddrLogin(Integer uid, String addr, String mobile, String name){
        if (name.equals("")&&addr.equals("")&&mobile.equals("")){
            if (view!=null){
                view.OnError("不能为空");
            }
            return;
        }
        addrModel.AddAddLogin(uid,addr,mobile,name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddAddBean>() {
                    @Override
                    public void accept(AddAddBean addAddBean) throws Exception {
                        view.OnAddAddUssed(addAddBean);
                    }
                });
    }
    public void UpdateAddrLogin(Integer uid,Integer addrid,String addr,String name,String mobile){
        if (name.equals("")&&addr.equals("")&&mobile.equals("")){
            if (view!=null){
                view.OnError("不能为空");
            }
            return;
        }
        addrModel.UpdateAddLogin(uid,addrid,addr,name,mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdateAddBean>() {
                    @Override
                    public void accept(UpdateAddBean updateAddBean) throws Exception {
                        view.OnUpdateAddUssed(updateAddBean);
                    }
                });
    }
    public void SetAddrLogin(Integer uid,Integer addrid,Integer status){
        addrModel.SetAddLogin(uid,addrid,status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SetAddBean>() {
                    @Override
                    public void accept(SetAddBean setAddBean) throws Exception {
                        view.OnSetAddUssed(setAddBean);
                    }
                });
    }
    public void GetDefaultLogin(Integer uid){
        addrModel.GetDefaultLogin(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetDefaultBean>() {
                    @Override
                    public void accept(GetDefaultBean getDefaultBean) throws Exception {
                        view.OnGetDefaultUssed(getDefaultBean);
                    }
                });
    }
}
