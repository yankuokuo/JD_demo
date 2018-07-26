package bwei.com.jd_demo.addr.view;

import bwei.com.jd_demo.addr.model.bean.AddAddBean;
import bwei.com.jd_demo.addr.model.bean.GetAddBean;
import bwei.com.jd_demo.addr.model.bean.GetDefaultBean;
import bwei.com.jd_demo.addr.model.bean.SetAddBean;
import bwei.com.jd_demo.addr.model.bean.UpdateAddBean;
import bwei.com.jd_demo.base.IView;

public interface AddrView extends IView{
    void OnAddAddUssed(AddAddBean addAddBean);
    void OnGetAddUssed(GetAddBean getAddBean);
    void OnGetDefaultUssed(GetDefaultBean getDefaultBean);
    void OnSetAddUssed(SetAddBean setAddBean);
    void OnUpdateAddUssed(UpdateAddBean updateAddBean);
    void OnError(String error);
}
