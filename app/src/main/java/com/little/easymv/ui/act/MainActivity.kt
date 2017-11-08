package com.little.easymv.ui.act

import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import com.jaydenxiao.common.baseapp.Router
import com.jaydenxiao.common.basemvvm.BaseActivity
import com.jaydenxiao.common.commonutils.ImageLoaderUtils
import com.jaydenxiao.common.commonutils.LogUtils
import com.little.easymv.HomePageAdapter
import com.little.easymv.R
import com.little.easymv.event.EventUI
import com.little.easymv.event.TO_TOP
import com.little.easymv.extension.postMessage
import com.little.easymv.vm.MainMV
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : BaseActivity<MainMV>(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.nav_manage)
        when (item.itemId) {
            R.id.nav_local_manga -> {
                LogUtils.loge("本地漫画")
                Router.from().to(CustomViewActivity::class.java).launch()
            }
        }

        dl_main_drawer.closeDrawer(Gravity.LEFT)
        return true;
    }

    override fun initData() {
//        val list = listOf<String>("1", "2", "3", "4")
//        val take = list.take(5)
//        LogUtils.loge("take -- $take  || list-- $list" )
        initView()


    }


    private fun initView() {
        //存在toolbar,关联ActionBarDrawerToggle控制侧边栏弹出
        val drawerToggle = ActionBarDrawerToggle(this, dl_main_drawer, R.string.drawer_open, R.string.drawer_close)
        drawerToggle.syncState()
        dl_main_drawer.addDrawerListener(drawerToggle)
        nv_main_navigation.setNavigationItemSelectedListener(this)

        val homePageAdapter = HomePageAdapter(supportFragmentManager, model.fragments, model.icons)
//        LogUtils.loge("$homePageAdapter")
        viewpager.setAdapter(homePageAdapter)
        viewpager.offscreenPageLimit = 3
        tabs.setupWithViewPager(viewpager)

        //菜单栏
        val headerView = nv_main_navigation.getHeaderView(0)
        //让item显示原本的颜色
//        nv_main_navigation.itemIconTintList = null
        ImageLoaderUtils.displayCircle(this, headerView.im_face, R.drawable.pig)
        ImageLoaderUtils.displayCircle(this, iv_user, R.drawable.pig)
        fab.setOnClickListener(this)
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
//                val drawable = ContextCompat.getDrawable(AppManager.getAppManager().currentActivity(), R.drawable.ic_launcher)
//                tab?.setIcon(drawable)
            }
        })
    }

    override fun onUpdate(type: Int) {

    }

    override fun onClick(v: View?) {
        postMessage(EventUI(TO_TOP))
    }

}
