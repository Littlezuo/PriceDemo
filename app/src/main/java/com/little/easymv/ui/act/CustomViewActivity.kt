package com.little.easymv.ui.act

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.jaydenxiao.common.basemvvm.BaseActivity
import com.jaydenxiao.common.basemvvm.ViewModel
import com.little.easymv.R
import kotlinx.android.synthetic.main.activity_custom_view.*

class CustomViewActivity : BaseActivity<ViewModel>() {
//    var pageModels = ArrayList<PageModel>()
    init {
//        pageModels.add(PageModel(R.layout.))
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_custom_view
    }


    var mViewList:MutableList<View> = mutableListOf()
    private val titles = arrayOf("图表")

    override fun initData() {
        super.initData()
        mViewList.add(View.inflate(mContext,R.layout.layout_pie,null))
        pager.adapter = AdapterViewpager(mViewList,titles)
        tabLayout.setupWithViewPager(pager)
    }

}
 class AdapterViewpager : PagerAdapter {
      lateinit var mViewlist:List<View>

     private lateinit var titles: Array<String>

     constructor(mViewlist: List<View>, titles: Array<String>) : super() {
         this.mViewlist = mViewlist
         this.titles = titles
     }

     override fun isViewFromObject(view: View?, any: Any?): Boolean {
         return view == any
     }

     override fun getCount(): Int {
        return mViewlist.size
     }

     override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
         container?.removeView(mViewlist.get(position))
     }

     override fun instantiateItem(container: ViewGroup?, position: Int): Any {
         container?.addView(mViewlist.get(position))
         return mViewlist.get(position)
     }

     override fun getPageTitle(position: Int): CharSequence {
         return titles.get(position)
     }
 }

//class PageModel {
//     var layoutRes : Int = 0
//    var titleRes :String = ""
//
//    constructor(layoutRes: Int, titleRes: String) {
//        this.layoutRes = layoutRes
//        this.titleRes = titleRes
//    }
//}
