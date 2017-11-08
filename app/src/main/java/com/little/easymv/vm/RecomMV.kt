package com.little.easymv.vm

import com.jaydenxiao.common.baserx.RxHelper
import com.little.easymv.adapter.FormatRecomBean
import com.little.easymv.api.Api
import com.little.easymv.api.BaseSubscriber
import com.little.easymv.api.HostType
import com.little.easymv.base.ListViewModel
import com.little.easymv.extension.formatRecom
import com.little.easymv.responsebean.RecommendResponse
import kotlinx.android.synthetic.main.fragment_recom.view.*

/**
 * Created by Littlezuo on 2017/8/31.
 */
class RecomMV : ListViewModel() {
//    var isErr = false
    override fun onStart() {
        super.onStart()

    }

    private fun handleData(recommendBean: List<RecommendResponse>) {

    }

    var formatRecoms = mutableListOf<FormatRecomBean>()
//    var formatRecoms:MutableList<> = mutableListOf<FormatRecomBean>()
    fun requestNet4recom() {
        Api.getDefault(HostType.KaBu).recommend
                .compose(RxHelper.handleErr())
//                .subscribe { recommendBean -> handleData(recommendBean) }
                .subscribe(object : BaseSubscriber<List<RecommendResponse>>(rootView.refreshLayout) {
                    override fun _onNext(recommendResponses: List<RecommendResponse>) {
                        formatRecoms = formatRecom(recommendResponses)
                        isErr =false
                        mVMListener.onUpdate(DEFAULT)
                    }

                    override fun _onError(message: String?) {
                        super._onError(message)
                        isErr = true
                        mVMListener.onUpdate(DEFAULT)
                    }
                })
    }

    override fun scroll2top() {
        rootView.recyVi.smoothScrollToPosition(0)
    }


}