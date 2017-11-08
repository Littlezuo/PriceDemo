package com.little.easymv.ui.act

import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jaydenxiao.common.basemvvm.ViewModel
import com.jaydenxiao.common.commonutils.StatusBarUtils
import com.little.easymv.R
import com.little.easymv.adapter.ComicAdapter
import com.little.easymv.base.SlideBaseActivity
import com.little.easymv.responsebean.ClassifyResponse
import com.little.easymv.ui.view.GridDivider
import com.little.easymv.ui.view.MyLoadMoreView
import com.little.easymv.vm.ComicClassifyMV
import com.little.easymv.vm.ComicDesMV
import kotlinx.android.synthetic.main.activity_comic_classify.*
import kotlinx.android.synthetic.main.include_toolbar_layout.*

class ComicClassifyActivity : SlideBaseActivity<ComicClassifyMV>(), BaseQuickAdapter.RequestLoadMoreListener {

    //上拉加载更多
    override fun onLoadMoreRequested() {
        model.page += 1
        model.requestNet4classifyDetail()
    }


    override fun getLayoutId(): Int {
        setStatusBar()

        return R.layout.activity_comic_classify
    }

    override fun initData() {
        super.initData()
        initView()
        initAdapter()
        model.requestNet4classifyDetail()
    }

    private fun initView() {
        tv_title.text = model.title
    }

    lateinit var comicAdapter: ComicAdapter
    private fun initAdapter() {
        comicAdapter = ComicAdapter(model.classifyList, comicRecyVi)
        val gridLayoutManager = GridLayoutManager(mContext, 3)
        comicRecyVi.setLayoutManager(gridLayoutManager)
        comicRecyVi.setAdapter(comicAdapter)
        comicAdapter.setLoadMoreView(MyLoadMoreView())
        comicAdapter.setOnLoadMoreListener(this, comicRecyVi)
        comicRecyVi.addItemDecoration(GridDivider())
        comicRecyVi.addOnItemTouchListener(object : OnItemClickListener() {
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val item = adapter?.data?.get(position) as ClassifyResponse
                ComicDesMV.start(item.id,item.title)
            }
        })

    }

    override fun onUpdate(type: Int) {
        when (type) {
            ViewModel.REFRESH -> refreshData()
            ViewModel.LOADMORE -> loadMoreData()
        }
    }

    private fun loadMoreData() {
        comicAdapter.addData(model.classifyList, model.isErr)
    }

    private fun refreshData() {
        comicAdapter.setNewData(model.classifyList, model.isErr)
//        comicAdapter.addData(model.classifyList,model.isErr)
    }

}
