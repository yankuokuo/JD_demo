package bwei.com.jd_demo.mvp.car.addbutton;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.jd_demo.R;

public class MyAddSubView extends LinearLayout {
    @BindView(R.id.sub_tv)
    TextView subTv;
    @BindView(R.id.number_tv)
    TextView numberTv;
    @BindView(R.id.add_tv)
    TextView addTv;
    private int number = 1;
    public MyAddSubView(Context context) {
        this(context, null);
    }

    public MyAddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.add_button, this);
        ButterKnife.bind(view);
    }
    @OnClick({R.id.sub_tv, R.id.add_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sub_tv:
                if (number>1){
                    --number;
                    numberTv.setText(number+"");
                    onNumBerChangeListener.onNumBerChange(number);
                }
                break;
            case R.id.add_tv:
                ++number;
                numberTv.setText(number+"");
                onNumBerChangeListener.onNumBerChange(number);
                break;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        numberTv.setText(number+"");
    }
    OnNumBerChangeListener onNumBerChangeListener;
    public void setOnNumBerChangeListener(OnNumBerChangeListener onNumBerChangeListener){
        this.onNumBerChangeListener=onNumBerChangeListener;
    }
    public  interface  OnNumBerChangeListener{
        void onNumBerChange(int num);
    }
}
