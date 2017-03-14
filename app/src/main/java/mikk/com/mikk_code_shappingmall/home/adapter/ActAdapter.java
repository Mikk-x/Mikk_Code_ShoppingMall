package mikk.com.mikk_code_shappingmall.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import mikk.com.mikk_code_shappingmall.home.bean.ResultBeanData;
import mikk.com.mikk_code_shappingmall.utils.Constants;

/**
 * Created by Mikk on 2017/3/9.
 */

public class ActAdapter extends PagerAdapter {


    private final Context mContext;
    private final List<ResultBeanData.ResultBean.ActInfoBean> act_info;

    public ActAdapter(Context mContext, List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
        this.mContext = mContext;
        this.act_info = act_info;
    }

    @Override
    public int getCount() {
        return act_info.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+act_info.get(position).getIcon_url()).into(imageView);
        container.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"position =="+position,Toast.LENGTH_SHORT).show();
            }
        });

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
