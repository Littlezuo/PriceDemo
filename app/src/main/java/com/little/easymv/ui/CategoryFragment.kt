package com.little.easymv.ui

import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jaydenxiao.common.basemvvm.BaseFragment
import com.jaydenxiao.common.basemvvm.ViewModel
import com.little.easymv.R
import com.little.easymv.adapter.CateGoryAdapter
import com.little.easymv.responsebean.CategoryResponse
import com.little.easymv.ui.view.MyRefreshHeader
import com.little.easymv.vm.CateMV
import com.little.easymv.vm.ComicClassifyMV
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.layout_recy.*


/**
 * A simple [Fragment] subclass.
 */
class CategoryFragment : BaseFragment<CateMV>(), OnRefreshListener {
    override fun onRefresh(refreshlayout: RefreshLayout?) {
        loadData()
    }
//    fun onRefresh() {
//
//    }



    override fun getLayoutResource(): Int {
        return R.layout.layout_recy
    }

    override fun lazyLoad() {
        initAdapter()
        initRefresh()
//        loadData()
    }

    private fun initRefresh() {
        refreshLayout.setOnRefreshListener(this)
        refreshLayout.refreshHeader = MyRefreshHeader(mContext)
        refreshLayout.setHeaderHeight(60f)
        refreshLayout.autoRefresh()//自动刷新
        refreshLayout.autoLoadmore()
    }

    private fun loadData() {
//        rootView.refreshLayout.isEnabled = true
        model.requestNet4CateData()

    }

    override fun onUpdate(flag: Int) {
        when (flag) {
            ViewModel.DEFAULT -> setData()
        }
    }


    var cateAdapter: CateGoryAdapter? = null
    private fun setData() {
//        rootView.refreshLayout.isEnabled = false
        cateAdapter?.setNewData(model.cateList, model.isErr)
    }

    private fun initAdapter() {
//        refreshLayout.initRefresh(true)
        cateAdapter = CateGoryAdapter(model.cateList, recyVi)
        cateAdapter?.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        val gridLayoutManager = GridLayoutManager(mContext, 3)
        recyVi.setLayoutManager(gridLayoutManager)
        recyVi.setAdapter(cateAdapter)
//        refreshLayout.setOnRefreshListener(this)
        cateAdapter?.setRetryListener { loadData() }
        recyVi.addOnItemTouchListener(object : OnItemClickListener() {
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                                val item = adapter?.data?.get(position) as CategoryResponse
                                ComicClassifyMV.open(item.tag_id,item.title)

            }
        })

    }

    companion object {
        fun newInstance():CategoryFragment {
            return CategoryFragment();
        }
    }

}