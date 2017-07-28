package com.example.xuan.mvpdemo.biz.backup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xuan.mvpdemo.bean.CityWeather;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : xuan.
 * Data : 2017/7/27.
 * Description :input the description of this file.
 */

public class BackupBiz implements IBackupBiz {
    private DatabaseHelper mHelper;

    public BackupBiz(Context context) {
        mHelper = new DatabaseHelper(context);
    }

    public void addCityWeather(CityWeather item) {
        addCityWeather(item, "0");
    }

    public void addCityWeather(CityWeather item, String islocal) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("insert into city(name,temperature,type,islocal) values(?,?,?,?)"
                , new Object[]{item.getName(), item.getTemperature()
                        , item.getType(), islocal});
        db.close();
    }


    public List<CityWeather> getAllCityWeather() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        List<CityWeather> data = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from city", null);
        while (cursor.moveToNext()) {
            CityWeather item = new CityWeather();
            item.setName(cursor.getString(1));
            item.setTemperature(cursor.getString(2));
            item.setType(cursor.getString(3));
            data.add(item);
        }
        cursor.close();
        db.close();
        return data;
    }

    @Override
    public boolean containCity(String name) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select COUNT(*) from city where name = ?", new String[]{name});
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        db.close();
        return count > 0;
    }

    @Override
    public void updateCity(CityWeather item) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("update city set name = ? , temperature = ?,type = ? where name = ?"
                , new Object[]{item.getName(), item.getTemperature()
                        , item.getType(), item.getName()});
        db.close();
    }

    @Override
    public boolean insertOrUpdateCity(CityWeather cityWeather) {
        return insertOrUpdateCity(cityWeather , "0");
    }

    @Override
    public boolean insertOrUpdateCity(CityWeather cityWeather, String islocal) {
        if (containCity(cityWeather.getName())) {
            updateCity(cityWeather);
            return true;
        } else {
            addCityWeather(cityWeather ,islocal);
            return false;
        }
    }

    @Override
    public void deleteCity(String city) {
        if (containCity(city)) {
            SQLiteDatabase db = mHelper.getWritableDatabase();
            db.execSQL("delete from city where name = ?"
                    , new Object[]{city});
            db.close();
        }
    }


}
