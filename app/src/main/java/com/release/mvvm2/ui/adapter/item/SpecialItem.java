package com.release.mvvm2.ui.adapter.item;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.release.mvvm2.bean.NewsItemInfoBean;

public class SpecialItem extends SectionEntity<NewsItemInfoBean> {

    public SpecialItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SpecialItem(NewsItemInfoBean newsItemBean) {
        super(newsItemBean);
    }
}
