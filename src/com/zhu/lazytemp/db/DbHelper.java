package com.zhu.lazytemp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.zhu.lazytemp.utils.LazyConstant;

/**
 * Created by zhudeshuai on 2014/11/15.
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, LazyConstant.Database.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table " + LazyConstant.Database.Tb_PlayList.TB_NAME +
                "("+
                LazyConstant.Database.Tb_PlayList.Field._ID + " int auto_increment primary key,"+
                LazyConstant.Database.Tb_PlayList.Field.FD_ID + " varchar(255),"+
                LazyConstant.Database.Tb_PlayList.Field.FD_ALBUM + " varchar(255),"+
                LazyConstant.Database.Tb_PlayList.Field.FD_ARTIST + " varchar(255),"+
                LazyConstant.Database.Tb_PlayList.Field.FD_DURATION + " varchar(255),"+
                LazyConstant.Database.Tb_PlayList.Field.FD_SIZE + " varchar(255),"+
                LazyConstant.Database.Tb_PlayList.Field.FD_TITLE + " varchar(255),"+
                LazyConstant.Database.Tb_PlayList.Field.FD_URL + " varchar(255)"
                +")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
