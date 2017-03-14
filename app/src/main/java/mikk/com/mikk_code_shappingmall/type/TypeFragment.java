package mikk.com.mikk_code_shappingmall.type;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import mikk.com.mikk_code_shappingmall.base.BaseFragment;

/**
 * 分类，TypeFragment.
 */

public class TypeFragment extends BaseFragment {

    private static final String TAG = "mikk";
    private TextView mTextView;
    @Override
    protected View initView() {
        Log.e(TAG,"TypeFragment的UI初始化了");
        mTextView = new TextView(mContext);
        mTextView.setText("分类");
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextSize(20);
        return mTextView;
    }

    @Override
    protected void initData() {
        super.initData();
        mTextView.setText("分类内容");
        Log.e(TAG,"TypeFragment的数据初始化了");
    }
}
