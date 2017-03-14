package mikk.com.mikk_code_shappingmall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mikk.com.mikk_code_shappingmall.base.BaseFragment;
import mikk.com.mikk_code_shappingmall.cart.fragment.CartFragment;
import mikk.com.mikk_code_shappingmall.communit.CommunityFragment;
import mikk.com.mikk_code_shappingmall.home.fragment.HomeFragment;
import mikk.com.mikk_code_shappingmall.type.TypeFragment;
import mikk.com.mikk_code_shappingmall.user.UserFragment;

public class MainActivity extends FragmentActivity {


    @Bind(R.id.fl_content)
    FrameLayout fl_content;
    @Bind(R.id.rb_home)
    RadioButton rb_home;
    @Bind(R.id.rb_type)
    RadioButton rb_type;
    @Bind(R.id.rb_community)
    RadioButton rb_community;
    @Bind(R.id.rb_cart)
    RadioButton rb_cart;
    @Bind(R.id.rb_user)
    RadioButton rb_user;
    @Bind(R.id.rg_main)
    RadioGroup rg_main;

    private List<BaseFragment> mBaseFragments;

    private int position;
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // 初始化View
        initView();
        // 初始化Fragment
        initFragment();
        // 设置RadioGroup的监听
        initListener();
    }

    private void initView() {

    }

    /**
     * 按照舒顺序添加
     */
    private void initFragment() {
        mBaseFragments = new ArrayList<>();
        mBaseFragments.add(new HomeFragment());
        mBaseFragments.add(new TypeFragment());
        mBaseFragments.add(new CommunityFragment());
        mBaseFragments.add(new CartFragment());
        mBaseFragments.add(new UserFragment());
    }

    private void initListener() {

        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }

                BaseFragment baseFragment = getFragment(position);
                switchFragment(tempFragment, baseFragment);

            }
        });

//        if (Constants.TIAOZHUAN == 1) {
//
//            rg_main.check(R.id.rb_cart);
//        }else {
//
//        }

        rg_main.check(R.id.rb_home);

    }



    /*//销毁所有的activity
    public void removeAll(){
        mikk.com.mikk_code_shappingmall.ActivityManager.getInstance().removeAll();
    }*/


    private BaseFragment getFragment(int position) {
        if (mBaseFragments != null && mBaseFragments.size() > 0) {
            BaseFragment baseFragment = mBaseFragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (!nextFragment.isAdded()) {
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.fl_content, nextFragment).commit();
                } else {
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }

        }
    }

}
