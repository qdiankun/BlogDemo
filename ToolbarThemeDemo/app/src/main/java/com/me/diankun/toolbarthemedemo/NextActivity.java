package com.me.diankun.toolbarthemedemo;

import android.os.Bundle;


public class NextActivity extends ToolbarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_next;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        //重新设置标题
        mToolbar.setTitle("NextActivity");
    }

}
