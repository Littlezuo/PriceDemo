package com.little.easymv.vm

import com.jaydenxiao.common.baserx.RxHelper
import com.little.easymv.api.Api
import com.little.easymv.api.BaseSubscriber
import com.little.easymv.api.HostType
import com.little.easymv.base.ListViewModel
import com.little.easymv.responsebean.CategoryResponse
import kotlinx.android.synthetic.main.layout_recy.view.*

/**
 * Created by Littlezuo on 2017/9/4.
 */
class CateMV : ListViewModel() {
    override fun scroll2top() {
        rootView.recyVi.smoothScrollToPosition(0)
    }

    //    var isErr = false
    override fun onStart() {
        super.onStart()
    }

    var cateList: MutableList<CategoryResponse>? = null;
    fun requestNet4CateData() {
//        rootView.refreshLayout.setVisibility(View.VISIBLE)

        Api.getDefault(HostType.KaBu).category
                .compose(RxHelper.handleErr())
                .subscribe(object : BaseSubscriber<MutableList<CategoryResponse>>() {
                    override fun _onNext(cateResponses: MutableList<CategoryResponse>) {
//                        formatRecoms = formatRecom(recommendResponses)
                        cateList = cateResponses
                        isErr = false
                        mVMListener.onUpdate(DEFAULT)
                        rootView.refreshLayout.finishRefresh()
//                        rootView.refreshLayout.alpha = 0f;
//                        rootView.refreshLayout.setVisibility(View.GONE)
                    }

                    override fun _onError(message: String?) {
                        super._onError(message)
                        isErr = true
                        mVMListener.onUpdate(DEFAULT)
                        rootView.refreshLayout.finishRefresh(false)
                    }
                })
    }


}