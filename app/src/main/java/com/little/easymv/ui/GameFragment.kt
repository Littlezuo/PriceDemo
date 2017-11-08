package com.little.easymv.ui

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jaydenxiao.common.basemvvm.BaseFragment
import com.little.easymv.R
import com.little.easymv.adapter.GameAdapter
import com.little.easymv.vm.GameMV
import kotlinx.android.synthetic.main.fragment_game.view.*

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : BaseFragment<GameMV>() {


    override fun getLayoutResource(): Int {
        return R.layout.layout_recy
    }

    override fun lazyLoad() {
        initAdapter()
        model.requestNet4gameList()
    }

    lateinit var gameAdapter:GameAdapter;
    private fun initAdapter() {
        gameAdapter = GameAdapter(model.gameList,rootView.recyVi)
        val layoutManager = LinearLayoutManager(mContext)
        rootView.recyVi.layoutManager = layoutManager
        rootView.recyVi.adapter = gameAdapter
        rootView.recyVi.addOnItemTouchListener (object : OnItemClickListener(){
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
//                adapter
            }
        })
    }


    companion object {
        fun newInstance(): Fragment {
            return GameFragment()
        }
    }

}
