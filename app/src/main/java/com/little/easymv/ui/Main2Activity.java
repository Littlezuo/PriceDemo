package com.little.easymv.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jaydenxiao.common.commonutils.ToastUitl;
import com.little.easymv.R;
import com.little.easymv.ui.view.MyAdapter;
import com.little.easymv.ui.view.customview.TestBean;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ArrayList list = new ArrayList<TestBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        list.add(new TestBean());
        list.add(new TestBean());
        list.add(new TestBean());
        list.add(new TestBean());
        list.add(new TestBean());
        MyAdapter myAdapter = new MyAdapter(list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyVi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUitl.showShort(list.toString());
                Log.e("kkkk", list.toString());
            }
        });
    }
}
