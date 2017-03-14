package mikk.com.mikk_code_shappingmall.user;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import mikk.com.mikk_code_shappingmall.base.BaseFragment;

/**
 * 个人中心，UserFragment.
 */

public class UserFragment extends BaseFragment {

    private static final String TAG = "mikk";
    private TextView mTextView;
    @Override
    protected View initView() {
        Log.e(TAG,"UserFragment的UI初始化了");
        mTextView = new TextView(mContext);
        mTextView.setText("个人中心");
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextSize(20);
        return mTextView;
    }

    @Override
    protected void initData() {
        super.initData();
        mTextView.setText("个人中心内容");
        Log.e(TAG,"UserFragment的数据初始化了");
    }
}
