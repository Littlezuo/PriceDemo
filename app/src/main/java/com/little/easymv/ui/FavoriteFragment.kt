package com.little.easymv.ui


import android.support.v4.app.Fragment

import com.jaydenxiao.common.basemvvm.BaseFragment
import com.jaydenxiao.common.basemvvm.ViewModel
import com.little.easymv.R


/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : BaseFragment<ViewModel>() {
    companion object {
        public fun newInstance():FavoriteFragment {
            return FavoriteFragment()
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.layout_recy
    }

}
