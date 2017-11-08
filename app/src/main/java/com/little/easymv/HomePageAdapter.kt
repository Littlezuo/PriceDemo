package com.little.easymv

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import com.jaydenxiao.common.baseapp.AppManager

/**
 * Created by Littlezuo on 2017/8/30.
 */

class HomePageAdapter : FragmentPagerAdapter {

    private val mFragment: List<Fragment>
    private val resIcon: IntArray

    constructor(fm: FragmentManager, mFragment: List<Fragment>, resIcon: IntArray) : super(fm) {
        this.mFragment = mFragment
        this.resIcon = resIcon
    }

    override fun getItem(position: Int): Fragment {
        return mFragment[position]
    }

    override fun getCount(): Int {
        return mFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        val resId = resIcon[position]
//        resIcon[position]
        val drawable = ContextCompat.getDrawable(AppManager.getAppManager().currentActivity(), resId)
        drawable.setBounds(0,0,drawable.intrinsicWidth,drawable.intrinsicHeight)
        val imageSpan = ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM)
        val spannableString = SpannableString(" ") //没有空格会越界
        spannableString.setSpan(imageSpan,0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }
}
