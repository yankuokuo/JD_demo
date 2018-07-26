package bwei.com.jd_demo.mvp.car.view;

import bwei.com.jd_demo.base.IView;
import bwei.com.jd_demo.mvp.car.model.bean.AddBean;
import bwei.com.jd_demo.mvp.car.model.bean.CarBean;
import bwei.com.jd_demo.mvp.car.model.bean.CreateBean;
import bwei.com.jd_demo.mvp.car.model.bean.DeleteBean;
import bwei.com.jd_demo.mvp.car.model.bean.UpdataCartBean;

public interface CarView extends IView {
    void onCarUssecssd(CarBean carBean);
    void onerror(String error);
    void DeleteUssecd(DeleteBean deleteBean);
    void UpdateUseecd(UpdataCartBean updataCartBean);
    void CreateUseccd(CreateBean createBean);
}
