package com.me.diankun.toolbarthemedemo;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends ToolbarActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.left_drawer)
    View drawerRootView;

    private ActionBarDrawerToggle mActionBarDrawerToggle;
    //搜索框
    private SearchView searchView;

    //点击的按钮
    @Bind(R.id.btn_next_activity)
    ButtonRectangle nextActivtyBtn;
    @Bind(R.id.btn_custom_dialog)
    ButtonRectangle customDialogBtn;
    @Bind(R.id.btn_library_dialog)
    ButtonRectangle libraryDialogBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDrawableToggle();

        //修改按钮的背景色
        changeBtnBg();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private void changeBtnBg() {
        //修改按钮的背景色为 colorPrimary
        nextActivtyBtn.setBackgroundColor(getColorPrimary());
        customDialogBtn.setBackgroundColor(getColorPrimary());
        libraryDialogBtn.setBackgroundColor(getColorPrimary());
    }

    @OnClick(R.id.btn_next_activity)
    void toNextActivity(View view) {
        Intent intent = new Intent(MainActivity.this, NextActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_custom_dialog)
    void showCustomDialog(View view) {
        Intent intent = new Intent(MainActivity.this, CustomDialogActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_library_dialog)
    void showLibraryDialog(View view) {
        Intent intent = new Intent(MainActivity.this, LibraryDialogActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        try {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            MenuItem searchItem = menu.findItem(R.id.action_search);
            //searchItem.expandActionView();
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            ComponentName componentName = getComponentName();

            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(componentName));
            searchView.setQueryHint(getString(R.string.search_note));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    //recyclerAdapter.getFilter().filter(s);
                    return true;
                }
            });
            MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    //recyclerAdapter.setUpFactor();
                    //refreshLayout.setEnabled(false);
                    return true;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    //refreshLayout.setEnabled(true);
                    return true;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_change_theme:
                showChangeThemeDialog();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 使用自带的Dialog
     */
    private void showChangeThemeDialogOld() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.change_theme);
        GridView gridView = (GridView) LayoutInflater.from(this).inflate(R.layout.colors_panel_layout, null);
        List<Integer> list = initColorDatas();
        final ColorChooseAdapter adapter = new ColorChooseAdapter(this, list);
        final int chooseValue = mPreferenceUtils.getIntParam(getString(R.string.choosed_theme_position));
        adapter.setCheckedPosition(chooseValue);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setCacheColorHint(0);
        gridView.setAdapter(adapter);
        builder.setView(gridView);
        final AlertDialog dialog = builder.show();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                //与当前主题不同，修改
                if (chooseValue != position) {
                    mPreferenceUtils.saveParam(getString(R.string.choosed_theme_position), position);
                    changeTheme();
                }
            }
        });
    }

    /**
     * 使用material-dialogs的自定义Dialog显示
     */
    private void showChangeThemeDialog() {
        //GridView
        GridView gridView = (GridView) LayoutInflater.from(this).inflate(R.layout.colors_panel_layout, null);
        List<Integer> list = initColorDatas();
        final ColorChooseAdapter adapter = new ColorChooseAdapter(this, list);
        final int chooseValue = mPreferenceUtils.getIntParam(getString(R.string.choosed_theme_position));
        adapter.setCheckedPosition(chooseValue);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setCacheColorHint(0);
        gridView.setAdapter(adapter);
        //Dialog
        boolean wrapInScrollView = false;
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title(R.string.change_theme)
                .customView(gridView, wrapInScrollView);
                //.positiveText(R.string.positive);
        final MaterialDialog dialog = builder.show();
        //设置点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                //与当前主题不同，修改
                if (chooseValue != position) {
                    mPreferenceUtils.saveParam(getString(R.string.choosed_theme_position), position);
                    changeTheme();
                }
            }
        });
    }

    private void changeTheme() {
        this.recreate();
    }

    private List<Integer> initColorDatas() {
        Integer[] colors = new Integer[]{R.drawable.red_round, R.drawable.brown_round, R.drawable.blue_round,
                R.drawable.blue_grey_round, R.drawable.yellow_round, R.drawable.deep_purple_round, R.drawable.pink_round,
                R.drawable.green_round};
        return Arrays.asList(colors);
    }


    private void initDrawableToggle() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                // TODO Auto-generated method stub
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                super.onDrawerOpened(drawerView);
            }
        };
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mDrawerLayout.setScrimColor(getColor(R.color.drawer_scrim_color));
    }


    //ToolBar 左上角添加三个横线
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
        if (mToolbar != null) {
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openOrCloseDrawer();
                }
            });
        }
    }

    private void openOrCloseDrawer() {
        if (mDrawerLayout.isDrawerOpen(drawerRootView)) {
            mDrawerLayout.closeDrawer(drawerRootView);
        } else {
            mDrawerLayout.openDrawer(drawerRootView);
        }
    }

    protected ThemeUtils.Theme getCurrentTheme() {
        int value = mPreferenceUtils.getIntParam(getString(R.string.choosed_theme_position), 0);
        return ThemeUtils.Theme.mapValue2Theme(value);
    }
}
