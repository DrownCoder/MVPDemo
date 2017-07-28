package com.example.xuan.mvpdemo.biz.backup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteOpenHelper;  
  
  
public class DatabaseHelper extends SQLiteOpenHelper {  
  
    //数据库名字  
    private static final String DB_NAME = "city.db";
  
    //本版号  
    private static final int VERSION = 1;  
  
    //创建表  
    private static final  String CREATE_TABLE_NOTE = "CREATE TABLE city(_id integer primary key autoincrement,"+
            "name text, temperature text, type text, islocal text)";
  
    //删除表  
    private static final String DROP_TABLE_NOTE = "drop table if exists city";
  
  
    public DatabaseHelper(Context context) {  
        super(context, DB_NAME, null, VERSION);  
    }  
  
    @Override  
    public void onCreate(SQLiteDatabase db) {  
        //SQLiteDatabase 用于操作数据库的工具类  
        db.execSQL(CREATE_TABLE_NOTE);  
    }  
  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
        db.execSQL(DROP_TABLE_NOTE);  
        db.execSQL(CREATE_TABLE_NOTE);  
    }  
} 