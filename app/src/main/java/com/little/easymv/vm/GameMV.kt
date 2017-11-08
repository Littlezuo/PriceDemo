package com.little.easymv.vm

import com.little.easymv.api.Api
import com.little.easymv.api.BaseSubscriber
import com.little.easymv.api.HostType
import com.little.easymv.base.ListViewModel
import com.little.easymv.responsebean.SubjectResopnse
import kotlinx.android.synthetic.main.fragment_game.view.*

/**
 * Created by Littlezuo on 2017/9/4.
 */

class GameMV : ListViewModel() {
    override fun onStart() {
        super.onStart()
    }

    override fun scroll2top() {
        rootView.recyVi.smoothScrollToPosition(0)
    }

     var gameList: MutableList<SubjectResopnse>? = null;
    private val page: Int = 0

    fun requestNet4gameList() {
        val params = "subject/0/$page.json"
        Api.getDefault(HostType.KaBu)
                .getSubject(params)
                .subscribe(object : BaseSubscriber<List<SubjectResopnse>>(){
                    override fun _onNext(t: List<SubjectResopnse>?) {
                    }
                })

    }
}
