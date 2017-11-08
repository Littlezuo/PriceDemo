package com.little.easymv.vm


import android.support.v4.app.Fragment
import com.jaydenxiao.common.basemvvm.ViewModel
import com.little.easymv.R
import com.little.easymv.ui.CategoryFragment
import com.little.easymv.ui.FavoriteFragment
import com.little.easymv.ui.GameFragment
import com.little.easymv.ui.RecomFragment

/**
 * Created by Littlezuo on 2017/8/28.
 */

class MainMV : ViewModel() {

    var fragments = kotlin.collections.arrayListOf<Fragment>()
    var data = mutableListOf<String>()
    val icons = intArrayOf(R.drawable.selector_tab_recom,R.drawable.selector_tab_favorite,  R.drawable.selector_tab_category, R.drawable.selector_tab_game)
    override fun onStart() {
        super.onStart()
        initFragments()
//        mVMListener.onUpdate(MainMV.TABS)
//        initResIcon()

    }

    private fun initFragments() {
        fragments.add(RecomFragment.newInstance())
        fragments.add(FavoriteFragment.newInstance())
        fragments.add(CategoryFragment.newInstance())
        fragments.add(GameFragment.newInstance())
    }

}
