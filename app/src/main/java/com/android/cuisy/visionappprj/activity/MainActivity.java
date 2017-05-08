package com.android.cuisy.visionappprj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import java.io.File;
import java.io.IOException;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.cuisy.visionappprj.R;
import com.android.cuisy.visionappprj.db.DBHelper;

public class MainActivity extends AppCompatActivity  implements  NavigationView.OnNavigationItemSelectedListener {
    private DBHelper myDBHelper;//创建一个继承SQLiteOpenHelper类实例
    private SQLiteDatabase mysql;

    //--------以下两个成员变量是针对在SD卡中存储数据库文件使用
    // private File path = new File("/sdcard/vision"); 创建目录
    // private File f = new File("/sdcard/vision/vision.db"); 创建文件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tree_list);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        myDBHelper = new DBHelper(this); //实例一个数据库辅助器
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera_list) {
            Intent intent = new Intent(MainActivity.this, TreeNodeActivity.class);
            startActivity(intent);
        }
        return true;
    }


}
