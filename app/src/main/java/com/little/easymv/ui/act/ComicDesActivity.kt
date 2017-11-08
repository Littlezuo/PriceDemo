package com.little.easymv.ui.act

import android.support.v7.widget.LinearLayoutManager
import com.jaydenxiao.common.basemvvm.ViewModel
import com.little.easymv.R
import com.little.easymv.adapter.ComicDesAdapter
import com.little.easymv.base.SlideBaseActivity
import com.little.easymv.vm.ComicDesMV
import kotlinx.android.synthetic.main.activity_comic_des.*
import kotlinx.android.synthetic.main.toolbar_title.*

class ComicDesActivity : SlideBaseActivity<ComicDesMV>() {


    override fun getLayoutId(): Int {
        setStatusBar()
        return R.layout.activity_comic_des
    }

    override fun initData() {
        super.initData()
        initView()
        initAdapter()
        model.reuqestNet4comicDes()
    }

    private fun initView() {
        tv_title.text = model.title
    }
    lateinit var comicDesAdapter:ComicDesAdapter
    private fun initAdapter() {
        comicDesAdapter= ComicDesAdapter(model.comicDes, recyViDes)
        val layoutManager = LinearLayoutManager(mContext)
        recyViDes.layoutManager = layoutManager
        recyViDes.adapter = comicDesAdapter
    }

    override fun onUpdate(type: Int) {
        super.onUpdate(type)
        when(type) {
            ViewModel.DEFAULT -> setData()
        }
    }

    private fun setData() {
        comicDesAdapter.setDesData(model.comicDes,model.isErr)
    }
}
