package com.little.easymv.ui.view;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.little.easymv.R;
import com.little.easymv.ui.view.customview.TestBean;

import java.util.List;

/**
 * Created by Littlezuo on 2017/11/8.
 */

public class MyAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {
    private final List<TestBean> list;

    public MyAdapter(@Nullable List<TestBean> data) {
        super(R.layout.item, data);
        this.list = data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, TestBean item) {
        EditText etPrice1 = helper.getView(R.id.price1);
        etPrice1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                list.get(helper.getLayoutPosition()).price1 = s.toString();
            }
        });
        EditText etPrice2 = helper.getView(R.id.price1);
        etPrice2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                list.get(helper.getLayoutPosition()).price2 = s.toString();
            }
        });
    }
}
