package bwei.com.jd_demo.mvp.fen.view;

import bwei.com.jd_demo.base.IView;
import bwei.com.jd_demo.mvp.fen.model.bean.ChildBean;
import bwei.com.jd_demo.mvp.fen.model.bean.ListBean;

public interface FenView extends IView {
    void onListgetUsseccd(ListBean listBean);
    void onchildgetUseeccd(ChildBean childBean);
    void onerror(String error);
}
