package com.example.yangzhe.learndatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by yangzhe on 16-8-15.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    /**
     * create table Sql sentence
     * */
    public static final String CREATE_MEITU_FAVORITES = "create table Favorites(" +
            "pictureUrl text primary key," +
            "title text," +
            "addTime text)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MEITU_FAVORITES);
        Toast.makeText(mContext,"Create Favorites table succeeded!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
