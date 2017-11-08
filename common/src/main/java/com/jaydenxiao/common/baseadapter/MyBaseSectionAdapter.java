package com.jaydenxiao.common.baseadapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.jaydenxiao.common.R;

import java.util.Collection;
import java.util.List;

/**
 * Created by Littlezuo on 2017/8/31.
 */

public abstract class MyBaseSectionAdapter<T extends SectionEntity >  extends BaseSectionQuickAdapter<T,BaseViewHolder>{
    private View netErrView;
    private View loadingView;
    private View notDataView;

    public MyBaseSectionAdapter(int layoutResId, int sectionHeadResId, List<T> data,RecyclerView recyclerView) {
        super(layoutResId, sectionHeadResId, data);
        initEmpty(recyclerView);
    }


    protected void initEmpty(RecyclerView recyclerView) {

        loadingView = ((Activity) mContext).getLayoutInflater().inflate(R.layout.loading_bg, (ViewGroup) recyclerView.getParent(), false);
        notDataView = ((Activity) mContext).getLayoutInflater().inflate(R.layout.empty_bg, (ViewGroup) recyclerView.getParent(), false);
        netErrView = ((Activity) mContext).getLayoutInflater().inflate(R.layout.neterr_bg, (ViewGroup) recyclerView.getParent(), false);
    }


    @Override
    public void addData(@NonNull Collection<? extends T> newData) {
        super.addData(newData);
        if (mData == null || mData.size() <= 0) {
            if (newData == null || newData.size() <= 0) {
                setEmptyView(notDataView);
            }
        } else {
            if (newData == null || newData.size() <= 0) {
                loadMoreEnd();
            } else {
                loadMoreComplete();
            }
        }
    }



    public void setNetErrView() {
        if (mData == null || mData.size() <= 0) {
            setEmptyView(notDataView);
        } else {
            loadMoreFail();
        }
    }

    @Override
    public void setNewData(@Nullable List<T> data) {
        super.setNewData(data);
        if (data == null || data.size() <= 0) {
            setEmptyView(notDataView);
        }
    }
}
