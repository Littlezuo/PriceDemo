package com.little.easymv.vm

import com.jaydenxiao.common.baseapp.Router
import com.jaydenxiao.common.basemvvm.SlideViewModel
import com.jaydenxiao.common.basemvvm.ViewModel
import com.jaydenxiao.common.baserx.RxHelper
import com.little.easymv.api.Api
import com.little.easymv.api.BaseSubscriber
import com.little.easymv.extension.EXTRA_COMIC_ID
import com.little.easymv.extension.EXTRA_COMIC_TITLE
import com.little.easymv.responsebean.ComicResponse1
import com.little.easymv.ui.act.ComicDesActivity

/**
 * Created by Littlezuo on 2017/9/6.
 */
class ComicDesMV : SlideViewModel() {
    var tag_id: Int? = -1
    var title: String? =""
    override fun onStart() {
        super.onStart()
        tag_id = Router.getInt(EXTRA_COMIC_ID)
        title = Router.getString(EXTRA_COMIC_TITLE)

    }

    companion object {
        fun start(tag_id: Int, title: String) {
            Router.fromWithAnim()
                    .putInt(EXTRA_COMIC_ID, tag_id)
                    .putString(EXTRA_COMIC_TITLE, title)
                    .to(ComicDesActivity::class.java).launch()
        }
    }
//    var mObjId = -1
    var comicDes: ComicResponse1? = null
    fun reuqestNet4comicDes() {
        val url = "comic/"+tag_id+  ".json"
        Api.getDefault().getComic(url)
                .compose(RxHelper.handleErr())
                .subscribe(object : BaseSubscriber<ComicResponse1>(mContext,true) {
                    override fun _onNext(t: ComicResponse1?) {
                        isErr = false
                        comicDes = t
                        mVMListener.onUpdate(ViewModel.DEFAULT)
                    }

                    override fun _onError(message: String?) {
                        super._onError(message)
                        isErr = true
                        mVMListener.onUpdate(ViewModel.DEFAULT)
                    }

                })
    }


}