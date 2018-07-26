package bwei.com.jd_demo.mvp.home.view;

import bwei.com.jd_demo.base.IView;
import bwei.com.jd_demo.mvp.car.model.bean.AddBean;
import bwei.com.jd_demo.mvp.home.model.bean.DetailBean;
import bwei.com.jd_demo.mvp.home.model.bean.JiuBean;
import bwei.com.jd_demo.mvp.home.model.bean.LunBean;
import bwei.com.jd_demo.mvp.home.model.bean.SearBean;

/**
 * Created by mumu on 2018/6/12.
 */

public interface ILoginView extends IView{
    void onLoginSuccess(LunBean lunBean);
    void onjiuLoginSuccess(JiuBean jiuBean);
    void onsearLoginSucess(SearBean searBean);
    void onDetailLoginSucess(DetailBean detailBean);
    void addUsseccd(AddBean addBean);
    void onerror(String error);
}

