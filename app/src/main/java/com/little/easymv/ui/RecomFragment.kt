package com.little.easymv.ui


//import com.jaydenxiao.common.baseevent.rxbus.Subscribe
//import kotlinx.android.synthetic.main.fragment_recom.*
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import com.jaydenxiao.common.basemvvm.BaseFragment
import com.jaydenxiao.common.basemvvm.ViewModel
import com.little.easymv.R
import com.little.easymv.adapter.FormatRecomBean
import com.little.easymv.adapter.RecomMultiItemAdapter
import com.little.easymv.ui.view.RecomDivider
import com.little.easymv.vm.RecomMV
import kotlinx.android.synthetic.main.fragment_recom.view.*


/**
 * A simple [Fragment] subclass.
 */
//@BindBus
class RecomFragment : BaseFragment<RecomMV>(), SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
//        refreshLayout.isRefreshing = true
        model.requestNet4recom()
    }


    override fun getLayoutResource(): Int {
        return R.layout.fragment_recom
    }



    companion object {
        public fun newInstance():RecomFragment {
            return RecomFragment()
        }
    }

    override fun lazyLoad() {
        initView()
        onRefresh()
    }

    private fun initView() {
        recomMultiItemAdapter = RecomMultiItemAdapter(model.formatRecoms, rootView.recyVi)
        rootView.recyVi.setLayoutManager(GridLayoutManager(mContext, FormatRecomBean.ALLSPAN))
        rootView.recyVi.setAdapter(recomMultiItemAdapter)
        rootView.refreshLayout.initRefresh(true)
        rootView.refreshLayout.setOnRefreshListener(this)

    }

    override fun onUpdate(flag: Int) {
        when(flag) {
            ViewModel.DEFAULT -> setData()
        }
    }
    lateinit var recomMultiItemAdapter: RecomMultiItemAdapter
    lateinit var recomDivider:RecomDivider
    private var initDecoration: Boolean = true

    private fun setData() {
        recomMultiItemAdapter.setNewData(model.formatRecoms)
        if (model.formatRecoms.isNotEmpty() && initDecoration) {
            initDecoration =false
            recomMultiItemAdapter.setSapn()
            recomDivider= RecomDivider(model.formatRecoms)
//        rootView.recyVi.removeItemDecoration(recomDivider)
            rootView.recyVi.addItemDecoration(recomDivider)
        }


    }



}
