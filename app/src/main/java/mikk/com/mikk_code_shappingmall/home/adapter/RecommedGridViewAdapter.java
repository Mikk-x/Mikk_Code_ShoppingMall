package mikk.com.mikk_code_shappingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mikk.com.mikk_code_shappingmall.R;
import mikk.com.mikk_code_shappingmall.home.bean.ResultBeanData;
import mikk.com.mikk_code_shappingmall.utils.Constants;

/**
 * Created by Mikk on 2017/3/9.
 */

public class RecommedGridViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ResultBeanData.ResultBean.RecommendInfoBean> datas;

    public RecommedGridViewAdapter(Context mContext, List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
        this.mContext = mContext;
        this.datas = recommend_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder ;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view,null);
            holder = new ViewHolder();
            holder.iv_recommend = (ImageView) convertView.findViewById(R.id.iv_recommend);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 根据位置得到相应的数据
        ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = datas.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+recommendInfoBean.getFigure()).into(holder.iv_recommend);
        holder.tv_name.setText(recommendInfoBean.getName());
        holder.tv_price.setText("¥"+recommendInfoBean.getCover_price());

        return convertView;
    }

    static class ViewHolder{

        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }

}
