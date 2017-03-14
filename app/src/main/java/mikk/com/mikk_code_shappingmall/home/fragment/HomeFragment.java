package mikk.com.mikk_code_shappingmall.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import mikk.com.mikk_code_shappingmall.R;
import mikk.com.mikk_code_shappingmall.base.BaseFragment;
import mikk.com.mikk_code_shappingmall.home.adapter.HomeFragmentAdapter;
import mikk.com.mikk_code_shappingmall.home.bean.ResultBeanData;
import mikk.com.mikk_code_shappingmall.utils.Constants;
import okhttp3.Call;

/**
 * 首页，HomeFragment.
 */

public class HomeFragment extends BaseFragment {

    private RecyclerView rv_home;
    private ImageButton ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;

    private HomeFragmentAdapter adapter;

    /**
     * 返回的数据
     */
    private ResultBeanData.ResultBean resultBean;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        rv_home = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageButton) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);

        initListener();

        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        // 联网请求
        getDataFromNet();
    }

    /**
     * 联网请求数据
     */
    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /**
                     * 请求失败的时候回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "首页请求失败 ==" + e.getMessage());
                    }

                    /**
                     * 当联网成功时候回调这个方法
                     * @param response  请求成功的数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "首页请求成功 ==" + response);
                        // 解析数据
                        processData(response);
                    }
                });
    }

    /**
     * 解析数据
     *
     * @param json
     */
    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        if (resultBean != null) {
            // 有数据 配置适配器
            adapter = new HomeFragmentAdapter(mContext,resultBean);
            rv_home.setAdapter(adapter);
            GridLayoutManager manager = new GridLayoutManager(mContext,1);
            // 设置跨度的监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {

                    if (position <= 4) {
                        ib_top.setVisibility(View.GONE);
                    }else {
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    // 只能返回1
                    return 1;
                }
            });
            // 设置布局管理者
            rv_home.setLayoutManager(manager);
        }else{
            // 没有数据
        }
        Log.e("TAG", "解析成功 ====" + resultBean.getHot_info().get(0).getName());
    }

    private void initListener() {
        ib_top.setOnClickListener(new MyOnClickListener());
        tv_search_home.setOnClickListener(new MyOnClickListener());
        tv_message_home.setOnClickListener(new MyOnClickListener());

    }

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ib_top:
                    // 回到顶部
                    rv_home.scrollToPosition(0);
                    break;
                case R.id.tv_search_home:
                    Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_message_home:
                    Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
