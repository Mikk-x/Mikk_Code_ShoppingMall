package mikk.com.mikk_code_shappingmall.cart.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mikk.com.mikk_code_shappingmall.R;
import mikk.com.mikk_code_shappingmall.base.BaseFragment;
import mikk.com.mikk_code_shappingmall.cart.adapter.CartRecyclerViewAdapter;
import mikk.com.mikk_code_shappingmall.cart.utils.CartStorage;
import mikk.com.mikk_code_shappingmall.home.bean.GoodsBean;

import static mikk.com.mikk_code_shappingmall.R.id.ll_empty_shopcart;

/**
 * 购物车，CartFragment.
 */

public class CartFragment extends BaseFragment {

    private static final String TAG = "mikk";
    @Bind(R.id.tv_shopcart_edit)
    TextView mTvShopcartEdit;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @Bind(R.id.checkbox_all)
    CheckBox mCheckboxAll;
    @Bind(R.id.tv_shopcart_total)
    TextView mTvShopcartTotal;
    @Bind(R.id.btn_check_out)
    Button mBtnCheckOut;
    @Bind(R.id.ll_check_all)
    LinearLayout mLlCheckAll;
    @Bind(R.id.cb_all)
    CheckBox mCbAll;
    @Bind(R.id.btn_delete)
    Button mBtnDelete;
    @Bind(R.id.btn_collection)
    Button mBtnCollection;
    @Bind(R.id.ll_delete)
    LinearLayout mLlDelete;
    @Bind(R.id.iv_empty)
    ImageView mIvEmpty;
    @Bind(R.id.tv_empty_cart_tobuy)
    TextView mTvEmptyCartTobuy;
    @Bind(ll_empty_shopcart)
    LinearLayout mLlEmptyShopcart;

    // 编辑状态
    private static final int ACTION_EDIT = 1;
    // 完成状态
    private static final int ACTION_COMPLETE = 2;


    private CartRecyclerViewAdapter mCartRecyclerViewAdapter;

    @Override
    protected View initView() {
        Log.e(TAG, "CartFragment的UI初始化了");
        View view = View.inflate(mContext, R.layout.fragment_cart, null);


        return view;
    }

    private void initListener() {
        mTvShopcartEdit.setTag(ACTION_EDIT);
        mTvShopcartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = (int) v.getTag();
                if (action == ACTION_EDIT) {
                    //切换为完成状态
                    showDelete();
                } else {
                    //切换成编辑状态
                    hideDelete();
                }
            }
        });
    }

    private void hideDelete() {

        //1.设置状态和文本-编辑
        mTvShopcartEdit.setTag(ACTION_EDIT);
        mTvShopcartEdit.setText("编辑");
        //2.变成非勾选
        if (mCartRecyclerViewAdapter != null) {
            mCartRecyclerViewAdapter.checkAll_none(true);
            mCartRecyclerViewAdapter.checkAll();
            mCartRecyclerViewAdapter.showTotalPrice();
        }
        //3.删除视图隐藏
        mLlDelete.setVisibility(View.GONE);
        //4.结算视图显示
        mLlCheckAll.setVisibility(View.VISIBLE);
    }

    private void showDelete() {
        //1.设置状态和文本-完成
        mTvShopcartEdit.setTag(ACTION_COMPLETE);
        mTvShopcartEdit.setText("完成");
        //2.变成非勾选
        if (mCartRecyclerViewAdapter != null) {
            mCartRecyclerViewAdapter.checkAll_none(false);
            mCartRecyclerViewAdapter.checkAll();
        }
        //3.删除视图显示
        mLlDelete.setVisibility(View.VISIBLE);
        //4.结算视图隐藏
        mLlCheckAll.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG, "CartFragment的数据初始化了");


    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    private void showData() {
        List<GoodsBean> goodsBeanList = CartStorage.getInstance().getAllData();
        if (goodsBeanList != null && goodsBeanList.size() > 0) {

            hideDelete();
            mTvShopcartEdit.setVisibility(View.VISIBLE);
            mLlCheckAll.setVisibility(View.VISIBLE);
            // 有数据
            // 把没有数据的UI隐藏
            mLlEmptyShopcart.setVisibility(View.GONE);
            // 设置适配器
            mCartRecyclerViewAdapter = new CartRecyclerViewAdapter
                    (mContext, goodsBeanList, mTvShopcartTotal, mCheckboxAll, mCbAll);
            mRecyclerview.setAdapter(mCartRecyclerViewAdapter);

            // 设置recyclerview布局管理器
            mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        } else {
            // 没有数据
            // 把没有数据的UI显示
            emptyShoppingCart();
        }
    }

    private void emptyShoppingCart() {

        mLlEmptyShopcart.setVisibility(View.VISIBLE);
        mTvShopcartEdit.setVisibility(View.GONE);
        mLlDelete.setVisibility(View.GONE);
    }

    @OnClick({R.id.btn_delete, R.id.btn_collection, R.id.btn_check_out})
    void cratListener(View view) {
        switch (view.getId()) {
            case R.id.btn_check_out:
                break;
            case R.id.btn_delete:
                // 删除选中的
                mCartRecyclerViewAdapter.deleteData();
                // 校验状态
                mCartRecyclerViewAdapter.checkAll();
                //数据大小为0
                if (mCartRecyclerViewAdapter.getItemCount() == 0) {
                    emptyShoppingCart();
                }

                break;
            case R.id.btn_collection:
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        initListener();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
