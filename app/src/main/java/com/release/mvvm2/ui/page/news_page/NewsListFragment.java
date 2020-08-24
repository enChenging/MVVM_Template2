package com.release.mvvm2.ui.page.news_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.release.base.base.BaseFragment;
import com.release.base.utils.LogUtils;
import com.release.mvvm2.BR;
import com.release.mvvm2.R;
import com.release.mvvm2.bean.NewsInfoBean;
import com.release.mvvm2.databinding.FragmentNewsListBinding;
import com.release.mvvm2.ui.adapter.NewsListAdapter;
import com.release.mvvm2.ui.adapter.item.NewsMultiItem;
import java.util.List;

import static com.release.mvvm2.utils.Constants.NEWS_TYPE_KEY;

/**
 * 要闻
 *
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class NewsListFragment extends BaseFragment<FragmentNewsListBinding, NewsListViewModel> {

    private static final String TAG = NewsListFragment.class.getSimpleName();

    NewsListAdapter mAdapter;
    private String mNewsId;


    public static NewsListFragment newInstance(String newsId) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_TYPE_KEY, newsId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_news_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initView(View view) {
        mNewsId = getArguments().getString(NEWS_TYPE_KEY);

        mAdapter = new NewsListAdapter(null);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        binding.rvNewsList.setHasFixedSize(true);//让RecyclerView避免重新计算大小
        binding.rvNewsList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvNewsList.setAdapter(mAdapter);

    }

    @Override
    public void initListener() {
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                viewModel.loadMoreData();
            }
        }, binding.rvNewsList);

        viewModel.finishLoadAdData.observe(this, new Observer<NewsInfoBean>() {
            @Override
            public void onChanged(NewsInfoBean newsInfoBean) {

            }
        });

        viewModel.finishLoadData.observe(this, new Observer<List<NewsMultiItem>>() {
            @Override
            public void onChanged(List<NewsMultiItem> newsMultiItems) {
                LogUtils.i(TAG, "loadData: " + newsMultiItems.size());
                mAdapter.setNewData(newsMultiItems);
            }
        });

        viewModel.finishLoadMoreData.observe(this, new Observer<List<NewsMultiItem>>() {
            @Override
            public void onChanged(List<NewsMultiItem> newsMultiItems) {
                LogUtils.i(TAG, "loadMoreData: " + newsMultiItems.size());
                mAdapter.loadMoreComplete();
                mAdapter.addData(newsMultiItems);
            }
        });


        viewModel.finishNoData.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                mAdapter.loadMoreEnd();
            }
        });
    }


    @Override
    public void updateViews(boolean isRefresh) {
        viewModel.loadData(this,isRefresh, mNewsId);
    }

}
