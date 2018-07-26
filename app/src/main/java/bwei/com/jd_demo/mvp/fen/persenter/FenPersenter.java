package bwei.com.jd_demo.mvp.fen.persenter;

import bwei.com.jd_demo.base.BasePresenter;
import bwei.com.jd_demo.mvp.fen.model.FenModel;
import bwei.com.jd_demo.mvp.fen.model.bean.ChildBean;
import bwei.com.jd_demo.mvp.fen.model.bean.ListBean;
import bwei.com.jd_demo.mvp.fen.view.FenView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FenPersenter extends BasePresenter<FenView> {
    FenView fenView;
    private  FenModel fenModel;

    public FenPersenter(FenView fenView) {
        super(fenView);
    }

    @Override
    protected void initModel() {
        fenModel = new FenModel();
    }

    public void fenLogin(){
        fenModel.listlogin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ListBean>() {
                    @Override
                    public void accept(ListBean listBean) throws Exception {
                        view.onListgetUsseccd(listBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view!=null){
                            view.onerror("aaa");
                        }
                    }
                });
    }
    public void childLogin(int cid){
        fenModel.childlogin(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ChildBean>() {
                    @Override
                    public void accept(ChildBean childBean) throws Exception {
                        view.onchildgetUseeccd(childBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view!=null){
                            view.onerror("ssss");
                        }
                    }
                });
    }
}
