package com.android.cuisy.visionappprj.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import com.android.cuisy.visionappprj.entity.Constants;

//定义User对象
public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "user";

    public static final String sql = "create table if not exists "
                + TABLE_NAME
                + "(id integer primary key autoincrement,"
                + "username varchar(50),"
                + "password varchar(20),"
                + "init_time datetime,"
                + "update_time datetime,"
                + "remark varchar(10))";
    private Context mContext;

    /*在Android中，创建和打开一个数据库都可以使用openOrCreateDatabase方法来实现，
      因为它会自动去检测是否存在这个数据库，若存在，则打开；若不存在，则创建一个新的
      创建成功，则返回一个SQLiteDatabase对象，否则抛出异常FileNotFoundException
    */
    public DBHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        mContext = context;
    }

    @Override
    //在数据库第一次生成的时候会调用这个方法，一般我们在这个方法里边生成数据库表
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        Toast.makeText(mContext, "Create Succeeded", Toast.LENGTH_SHORT).show();
    }
    @Override
    /* 一般默认情况下，当我们插入数据库后，就立即更新
       当数据库需要升级的时候，Android 系统会主动的调用这个方法。
       一般我们在这个方法里边删除数据表，并建立新的数据表，
       当然是否还需要做其他的操作，完全取决于项目需求
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists" + TABLE_NAME);
    }

//保存一条记录
    void addUser() {
    }
    void modify(){

    }
}
