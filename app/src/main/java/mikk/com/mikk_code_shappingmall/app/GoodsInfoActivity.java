package mikk.com.mikk_code_shappingmall.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mikk.com.mikk_code_shappingmall.R;
import mikk.com.mikk_code_shappingmall.cart.utils.CartStorage;
import mikk.com.mikk_code_shappingmall.home.bean.GoodsBean;
import mikk.com.mikk_code_shappingmall.utils.Constants;

import static mikk.com.mikk_code_shappingmall.R.id.iv_good_info_image;

public class GoodsInfoActivity extends Activity {

    @Bind(R.id.ib_good_info_back)
    ImageButton mIbGoodInfoBack;
    @Bind(R.id.ib_good_info_more)
    ImageButton mIbGoodInfoMore;
    @Bind(iv_good_info_image)
    ImageView mIvGoodInfoImage;
    @Bind(R.id.tv_good_info_name)
    TextView mTvGoodInfoName;
    @Bind(R.id.tv_good_info_desc)
    TextView mTvGoodInfoDesc;
    @Bind(R.id.tv_good_info_price)
    TextView mTvGoodInfoPrice;
    @Bind(R.id.tv_good_info_store)
    TextView mTvGoodInfoStore;
    @Bind(R.id.tv_good_info_style)
    TextView mTvGoodInfoStyle;
    @Bind(R.id.wb_good_info_more)
    WebView mWbGoodInfoMore;
    @Bind(R.id.tv_good_info_callcenter)
    TextView mTvGoodInfoCallcenter;
    @Bind(R.id.tv_good_info_collection)
    TextView mTvGoodInfoCollection;
    @Bind(R.id.tv_good_info_cart)
    TextView mTvGoodInfoCart;
    @Bind(R.id.btn_good_info_addcart)
    Button mBtnGoodInfoAddcart;
    @Bind(R.id.ll_goods_root)
    LinearLayout mLlGoodsRoot;
    @Bind(R.id.tv_more_share)
    TextView mTvMoreShare;
    @Bind(R.id.tv_more_search)
    TextView mTvMoreSearch;
    @Bind(R.id.tv_more_home)
    TextView mTvMoreHome;
    @Bind(R.id.btn_more)
    Button mBtnMore;
    @Bind(R.id.ll_root)
    LinearLayout mLlRoot;
    @Bind(R.id.activity_goods_info)
    LinearLayout mActivityGoodsInfo;


    private TextView tv_more_share;
    private TextView tv_more_search;
    private TextView tv_more_home;
    private Button btn_more;
    private GoodsBean goodsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);

        initView();

        // 接收数据
        goodsBean = (GoodsBean) getIntent().getSerializableExtra("goods_bean");
        // 判断是否有数据
        if (goodsBean != null) {
//            Toast.makeText(this,"goodsbean =="+goodsBean.toString(), Toast.LENGTH_SHORT).show();
            // 有数据
            setDataForView(goodsBean);
        }

    }

    /**
     * 设置数据
     *
     * @param goodsBean
     */
    private void setDataForView(GoodsBean goodsBean) {
        // 设置图片
//        iv_good_info_image
        Glide.with(this).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(mIvGoodInfoImage);

        // 设置价格
        mTvGoodInfoPrice.setText("¥" + goodsBean.getCover_price());

        // 设置名称
        mTvGoodInfoName.setText(goodsBean.getName());

        // 设置详情
        setWebViewData(goodsBean.getProduct_id());
    }

    private void setWebViewData(String product_id) {
        if (product_id != null) {
            mWbGoodInfoMore.loadUrl("https://www.baidu.com");
            // 设置支持JavaScript
            WebSettings webSettings = mWbGoodInfoMore.getSettings();
            webSettings.setUseWideViewPort(true); // 支持双击页面变大变小
            webSettings.setJavaScriptEnabled(true); // 设置支持JavaScript
            // 优先使用缓存
            webSettings.setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);
            // 跳转到系统浏览器
            mWbGoodInfoMore.setWebViewClient(new WebViewClient() {
                // 低版本
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    return true;
                }

                // 高版本
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.loadUrl(request.getUrl().toString());
                    }
                    return true;
                }
            });
        }
    }

    private void initView() {
        tv_more_share = (TextView) findViewById(R.id.tv_more_share);
        tv_more_search = (TextView) findViewById(R.id.tv_more_search);
        tv_more_home = (TextView) findViewById(R.id.tv_more_home);
        btn_more = (Button) findViewById(R.id.btn_more);
    }

    @OnClick({R.id.ib_good_info_back, R.id.ib_good_info_more, R.id.btn_good_info_addcart,
            R.id.tv_good_info_callcenter, R.id.tv_good_info_collection, R.id.tv_good_info_cart,
            R.id.tv_more_share, R.id.tv_more_search, R.id.tv_more_home})
    void initListener(View view) {
        switch (view.getId()) {
            case R.id.ib_good_info_back:
                finish();
                break;
            case R.id.ib_good_info_more:
                Toast.makeText(GoodsInfoActivity.this, "更多", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_good_info_addcart:
                CartStorage.getInstance().addData(goodsBean);
                Toast.makeText(GoodsInfoActivity.this, "添加到购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_callcenter:
                Toast.makeText(GoodsInfoActivity.this, "联系客服", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_collection:
                Toast.makeText(GoodsInfoActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_cart:
                Toast.makeText(GoodsInfoActivity.this, "购物车", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(GoodsInfoActivity.this,MainActivity.class);
//                Constants.TIAOZHUAN=1;
//                startActivity(intent);
//
//                finish();
                break;
            case R.id.tv_more_share:
                Toast.makeText(GoodsInfoActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_search:
                Toast.makeText(GoodsInfoActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_home:
                Toast.makeText(GoodsInfoActivity.this, "主页面", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
