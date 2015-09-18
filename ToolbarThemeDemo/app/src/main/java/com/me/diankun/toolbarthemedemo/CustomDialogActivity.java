package com.me.diankun.toolbarthemedemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;

import butterknife.Bind;


public class CustomDialogActivity extends ToolbarActivity implements View.OnClickListener {

    @Bind(R.id.btn_custom_aletdialog1)
    ButtonRectangle custoRectangle1;
    @Bind(R.id.btn_custom_aletdialog2)
    ButtonRectangle custoRectangle2;
    @Bind(R.id.btn_custom_aletdialog3)
    ButtonRectangle custoRectangle3;
    @Bind(R.id.btn_custom_aletdialog4)
    ButtonRectangle custoRectangle4;
    @Bind(R.id.btn_custom_aletdialog5)
    ButtonRectangle custoRectangle5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //修改按钮的背景色和设置监听
        changeBtnBg();
    }

    private void changeBtnBg() {
        custoRectangle1.setBackgroundColor(getColorPrimary());
        custoRectangle2.setBackgroundColor(getColorPrimary());
        custoRectangle3.setBackgroundColor(getColorPrimary());
        custoRectangle4.setBackgroundColor(getColorPrimary());
        custoRectangle5.setBackgroundColor(getColorPrimary());

        custoRectangle1.setOnClickListener(this);
        custoRectangle2.setOnClickListener(this);
        custoRectangle3.setOnClickListener(this);
        custoRectangle4.setOnClickListener(this);
        custoRectangle5.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_dialog;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_dialog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_custom_aletdialog1:
                showDialog1();
                break;
            case R.id.btn_custom_aletdialog2:
                showListDialog();
                break;
            case R.id.btn_custom_aletdialog3:
                showSingleDialog();
                break;
            case R.id.btn_custom_aletdialog4:
                showMultiDialog();
                break;
            case R.id.btn_custom_aletdialog5:
                showCustomDialog();
                break;
            default:
                break;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登陆");//标题
        final View root = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null);
        builder.setView(root);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // 获取结果
                EditText edit = (EditText) root.findViewById(R.id.editText);
                EditText edit2 = (EditText) root.findViewById(R.id.editText2);
                Toast.makeText(CustomDialogActivity.this, "用户名："+edit.getText().toString()+"密码："+edit2.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        // 添加一个按钮，并监听按钮事件 (消极的;否认的)
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(CustomDialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void showMultiDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dialog2");//标题
        //builder.setIcon(R.mipmap.ic_launcher);//设置对话图标
        //builder.setMessage("对话框内容");//对话框内容
        //列表
        CharSequence[] chars = {"hello", "world", "nihao", "shijie"};
        boolean[] checked = new boolean[]{false, false, false, false};
        builder.setMultiChoiceItems(chars, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(CustomDialogActivity.this, "点击了" + which + "位置   " + String.valueOf(isChecked), Toast.LENGTH_SHORT).show();
            }
        });
        // 添加一个按钮，并监听按钮事件 (积极的;确实的)
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(CustomDialogActivity.this, "PositiveButton", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void showSingleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dialog2");//标题
        //builder.setIcon(R.mipmap.ic_launcher);//设置对话图标
        //builder.setMessage("对话框内容");//对话框内容
        //列表
        CharSequence chars[] = {"hello", "world", "nihao", "shijie"};
        builder.setSingleChoiceItems(chars, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 点击了第几列
                Toast.makeText(CustomDialogActivity.this, "点击了" + which + "位置", Toast.LENGTH_SHORT).show();
            }
        });

        // 添加一个按钮，并监听按钮事件 (积极的;确实的)
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(CustomDialogActivity.this, "PositiveButton", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void showListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dialog2");//标题
        //builder.setIcon(R.mipmap.ic_launcher);//设置对话图标
        //builder.setMessage("对话框内容");//对话框内容
        //列表
        CharSequence chars[] = {"hello", "world", "nihao", "shijie"};
        builder.setItems(chars, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // 点击了第几列
                Toast.makeText(CustomDialogActivity.this, "点击了" + which + "位置", Toast.LENGTH_SHORT).show();
                // ... 动作
            }
        });
        // 添加一个按钮，并监听按钮事件 (积极的;确实的)
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(CustomDialogActivity.this, "PositiveButton", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void showDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dialog1");//标题
        //builder.setIcon(R.mipmap.ic_launcher);//设置对话图标
        builder.setMessage("对话框内容");//对话框内容

        // 添加一个按钮，并监听按钮事件 (积极的;确实的)
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(CustomDialogActivity.this, "PositiveButton", Toast.LENGTH_SHORT).show();
            }
        });
        // 添加一个按钮，并监听按钮事件 (消极的;否认的)
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(CustomDialogActivity.this, "NegativeButton", Toast.LENGTH_SHORT).show();
            }
        });
        // 添加一个按钮，并监听按钮事件 (中立的)
        builder.setNeutralButton("帮助", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(CustomDialogActivity.this, "NeutralButton", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
