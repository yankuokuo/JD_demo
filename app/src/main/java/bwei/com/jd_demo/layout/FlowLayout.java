package bwei.com.jd_demo.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup{
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
         int linWidth=0;
         int totalHeight=0;
         View childView;
         int childWidth=0;
         int childHeight=0;
        for (int j = 0; j <getChildCount(); j++) {
            childView=getChildAt(j);
            childWidth=childView.getMeasuredWidth();
            childHeight=childView.getMeasuredHeight();
            if (childWidth+linWidth>getMeasuredWidth()){
                totalHeight+=childHeight;
                linWidth=0;
                childViewLayout(childView,linWidth,totalHeight,linWidth+childWidth,totalHeight+childHeight);
                linWidth=childWidth;
            }else{
                childViewLayout(childView,linWidth,totalHeight,linWidth+childWidth,totalHeight+childHeight);
                linWidth+=childWidth;
            }
        }
    }
    public void childViewLayout(View childView,int l,int t,int r,int b){
        childView.layout(l,t,r,b);
    }
}
