package mikk.com.mikk_code_shappingmall.communit;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import mikk.com.mikk_code_shappingmall.base.BaseFragment;

/**
 * 发现，CommunityFragment.
 */

public class CommunityFragment extends BaseFragment {

    private static final String TAG = "mikk";
    private TextView mTextView;
    @Override
    protected View initView() {
        Log.e(TAG,"CommunityFragment的UI初始化了");
        mTextView = new TextView(mContext);
        mTextView.setText("发现");
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextSize(20);
        return mTextView;
    }

    @Override
    protected void initData() {
        super.initData();
        mTextView.setText("发现内容");
        Log.e(TAG,"CommunityFragment的数据初始化了");
    }
}
