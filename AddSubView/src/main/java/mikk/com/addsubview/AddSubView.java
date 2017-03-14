package mikk.com.addsubview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Mikk on 2017/3/11.
 */

public class AddSubView extends LinearLayout implements View.OnClickListener {

    private final Context mContext;
    private ImageView iv_sub;
    private ImageView iv_add;
    private TextView tv_value;
    // 当前值
    private int value = 1;
    // 最小值
    private int minValue = 1;
    // 最大值
    private int maxValue = 5;

    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        View.inflate(context,R.layout.add_sub_view,this);
        iv_sub = (ImageView) findViewById(R.id.iv_sub);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        tv_value = (TextView) findViewById(R.id.tv_value);

        int  value  = getValue();
        setValue(value);
        //设置点击事件
        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 减
            case R.id.iv_sub:
                subNumber();
                break;
            // 加
            case R.id.iv_add:
                addNumber();
                break;
        }
    }

    private void addNumber() {
        if(value < maxValue){
            value ++;
        }
        setValue(value);

        if(onNumberChangeListener !=null){
            onNumberChangeListener.onNumberChange(value);
        }
    }

    private void subNumber() {
        if(value > minValue){
            value --;
        }
        setValue(value);

        if(onNumberChangeListener !=null){
            onNumberChangeListener.onNumberChange(value);
        }
    }


    public int getValue() {

        String valueStr =  tv_value.getText().toString().trim();
        if(!TextUtils.isEmpty(valueStr)){
            value = Integer.parseInt(valueStr) ;
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(value+"");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * 当数量发生变化的时候回调
     */
    public interface OnNumberChangeListener{
        /**
         * 当数据发生变化的时候回调
         * @param value
         */
        public void onNumberChange(int value);
    }

    private  OnNumberChangeListener onNumberChangeListener;

    /**
     * 设置数量变化的监听
     * @param onNumberChangeListener
     */
    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }

}
