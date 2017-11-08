package com.little.easymv.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseViewHolder
import com.jaydenxiao.common.baseadapter.MyBaseQuickAdapter
import com.jaydenxiao.common.baseadapter.MyMultiItemAdapter
import com.jaydenxiao.common.commonutils.FormatUtil
import com.jaydenxiao.common.commonutils.TimeUtil
import com.little.easymv.R
import com.little.easymv.bean.ComicTypeBean
import com.little.easymv.extension.calculateSpan
import com.little.easymv.extension.loadImage
import com.little.easymv.responsebean.ComicResponse1
import kotlinx.android.synthetic.main.item_chapter.view.*
import kotlinx.android.synthetic.main.item_comic_des_chapter.view.*
import kotlinx.android.synthetic.main.item_comic_des_header.view.*

/**
 * Created by Littlezuo on 2017/9/6.
 */
class ComicDesAdapter : MyMultiItemAdapter<ComicTypeBean> {
    //    init {
//        val list = listOf<ComicTypeBean>(ComicTypeBean(ComicTypeBean.HEADER), ComicTypeBean(ComicTypeBean.CHAPTER), ComicTypeBean(ComicTypeBean.COMMENT))
//
//    }
    companion object {
        val list = listOf<ComicTypeBean>(ComicTypeBean(ComicTypeBean.HEADER), ComicTypeBean(ComicTypeBean.CHAPTER), ComicTypeBean(ComicTypeBean.COMMENT))
    }

    var comicBean: ComicResponse1? = null

    constructor(data: ComicResponse1?, recyclerView: RecyclerView) : super(list, recyclerView) {
        comicBean = data
        addItemType(ComicTypeBean.HEADER, R.layout.item_comic_des_header)
        addItemType(ComicTypeBean.CHAPTER, R.layout.item_comic_des_chapter)
        addItemType(ComicTypeBean.COMMENT, R.layout.item_comic_des_comment)
    }

    override fun convert(helper: BaseViewHolder, itemView: View, item: ComicTypeBean) {
        when (item.itemType) {
            ComicTypeBean.HEADER -> setHeader(helper, itemView, item)
            ComicTypeBean.CHAPTER -> setChapter(helper, itemView, item)
            ComicTypeBean.COMMENT -> setComment(helper, itemView, item)
        }
    }

    private fun setHeader(helper: BaseViewHolder, itemView: View, item: ComicTypeBean) {
        if (comicBean != null) {
            loadImage(mContext, itemView.iv_cover, comicBean?.cover)
//            helper.setText(R.id.tv_title,comicBean?.title)
            itemView.tv_title.text = comicBean?.title
            itemView.authors.text = comicBean?.authors?.get(0)?.tag_name + "/" + comicBean?.types?.get(0)?.tag_name
            itemView.battle_num.text = mContext.getString(R.string.battle_num, FormatUtil.conNum(comicBean?.hot_num, 2))
            itemView.subscribe.text = mContext.getString(R.string.subscribe_num, FormatUtil.conNum(comicBean?.subscribe_num?.toLong(), 2))
            itemView.comic_des.setText(comicBean!!.description)
        }

    }

    private fun setComment(helper: BaseViewHolder, itemView: View, item: ComicTypeBean) {

    }

    //    var subChapterList: List<ComicResponse1.ChaptersBean.DataBean>? = null
    var byOrder = false //按递增顺序
    var isExpanse = false
    var span: Int = 4
    //    private val itemClick: View.OnClickListener? = View.OnClickListener { view -> onItemClick(view) }
    lateinit var chapterItemAdapter: ChapterItemAdapter

    private fun onItemClick(view: View) {
        when (view.id) {
            R.id.chapter_order -> {
                byOrder = !byOrder
                (view as TextView).text = if (byOrder) "正序" else "倒序"
                notifyData()

            }
            R.id.tv_show_all -> {
                isExpanse = !isExpanse
                (view as TextView).text = if (isExpanse) "收起全部章节" else "查看全部章节"
                notifyData()
            }
        }
    }

    private fun notifyData() {
        var showList = if (byOrder) chapter?.data?.reversed() else chapter?.data //先反转后截取
        showList = if (isExpanse) showList else showList?.take(span * 4)
        chapterItemAdapter.setNewData(showList)
    }

    //    private fun reverOrder() {
//
//    }
    var chapter: ComicResponse1.ChaptersBean? = null

    private fun setChapter(helper: BaseViewHolder, itemView: View, bean: ComicTypeBean) {
        //绑定title
        chapter = comicBean?.chapters?.get(0)
        itemView.chatper_title.text = mContext.getString(R.string.chapter_update, chapter?.title, TimeUtil.getStringByFormatSec(chapter?.data?.get(0)?.updatetime?.toLong(), "yyyy-MM-dd"))
        itemView.chapter_order.text = if (byOrder) "正序" else "倒序"
        val length = chapter?.data?.get(0)?.chapter_title?.length
        span = calculateSpan(length)
        //绑定chatper
        itemView.chapter_recy.layoutManager = GridLayoutManager(mContext, span)

        var showList = if (byOrder) chapter?.data?.reversed() else chapter?.data //先反转后截取
        showList = if (isExpanse) showList else showList?.take(span * 4)
//        var showList = if (isExpanse) chapter?.data else chapter?.data?.take(span * 4)
//        if (byOrder)showList =  showList?.reversed()
        chapterItemAdapter = ChapterItemAdapter(showList?.toMutableList())
        itemView.chapter_recy.adapter = chapterItemAdapter
        itemView.chapter_order.setOnClickListener { view -> onItemClick(view) }
        itemView.tv_show_all.setOnClickListener { view -> onItemClick(view) }
    }


    fun setDesData(comicDes: ComicResponse1?, err: Boolean) {
        comicBean = comicDes
        setNewData(list, err)
    }

}

class ChapterItemAdapter(data: MutableList<ComicResponse1.ChaptersBean.DataBean>?) : MyBaseQuickAdapter<ComicResponse1.ChaptersBean.DataBean>(R.layout.item_chapter, data) {

    override fun convert(helper: BaseViewHolder?, itemView: View, item: ComicResponse1.ChaptersBean.DataBean?) {
        itemView.tv_item_chapter.text = item?.chapter_title
    }
}