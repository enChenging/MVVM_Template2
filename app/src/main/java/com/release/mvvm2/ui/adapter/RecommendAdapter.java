package com.release.mvvm2.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.release.base.utils.DefIconFactory;
import com.release.base.utils.ImageLoader;
import com.release.mvvm2.R;
import com.release.mvvm2.bean.RecommendPageBean;

import java.util.List;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class RecommendAdapter extends BaseQuickAdapter<RecommendPageBean.NewslistBean, BaseViewHolder> {


    public RecommendAdapter(int layoutResId, @Nullable List<RecommendPageBean.NewslistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendPageBean.NewslistBean item) {

        ImageLoader.loadFitCenter(mContext, item.getPicUrl(), helper.getView(R.id.iv_tuijian), DefIconFactory.provideIcon());
        helper.setText(R.id.tv_tuijian_title, item.getTitle());
        helper.setText(R.id.tv_tuijian_time, item.getCtime());
    }
}
