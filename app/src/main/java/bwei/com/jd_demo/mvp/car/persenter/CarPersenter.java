package bwei.com.jd_demo.mvp.car.persenter;

import bwei.com.jd_demo.base.BasePresenter;
import bwei.com.jd_demo.mvp.car.model.CarModel;
import bwei.com.jd_demo.mvp.car.model.bean.AddBean;
import bwei.com.jd_demo.mvp.car.model.bean.CarBean;
import bwei.com.jd_demo.mvp.car.model.bean.CreateBean;
import bwei.com.jd_demo.mvp.car.model.bean.DeleteBean;
import bwei.com.jd_demo.mvp.car.model.bean.UpdataCartBean;
import bwei.com.jd_demo.mvp.car.view.CarView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CarPersenter extends BasePresenter<CarView> {

    private CarModel carModel;

    public CarPersenter(CarView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        carModel = new CarModel();
    }
    public void carLogin(Integer uid){
        carModel.carmodel(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarBean>() {
                    @Override
                    public void accept(CarBean carBean) throws Exception {
                        view.onCarUssecssd(carBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view!=null){
                            view.onerror("不能为空");
                        }
                    }
                });
    }

    public void DeleteLogin(Integer uid,Integer pid){
        carModel.deletemodel(uid,pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DeleteBean>() {
                    @Override
                    public void accept(DeleteBean deleteBean) throws Exception {
                        view.DeleteUssecd(deleteBean);
                    }
                });

    }
    public void UpdateLogin(Integer uid,String sellerid,Integer pid,Integer num,Integer selected){
        carModel.updateLogin(uid,sellerid,pid,num,selected)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdataCartBean>() {
                    @Override
                    public void accept(UpdataCartBean updataCartBean) throws Exception {
                         view.UpdateUseecd(updataCartBean);
                    }
                });
    }
    public void CreateLogin(Integer uid,float price){
        carModel.CreateLogin(uid,price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CreateBean>() {
                    @Override
                    public void accept(CreateBean createBean) throws Exception {
                        view.CreateUseccd(createBean);
                    }
                });
    }
}
