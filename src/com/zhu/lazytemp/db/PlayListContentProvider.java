package com.zhu.lazytemp.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.zhu.lazytemp.utils.LazyConstant;

/**
 * Created by zhudeshuai on 2014/11/17.
 */
public class PlayListContentProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final String AUTHORITY = "com.zhu.lazytemp.PlayListContentProvider";

    private static final int QUERY = 1;

    private static final int INSERT = 2;

    private static final int DELETE = 3;

    private static final int UPDATE = 4;

    static {
        sUriMatcher.addURI(AUTHORITY,"query",QUERY);
        sUriMatcher.addURI(AUTHORITY,"insert",INSERT);
        sUriMatcher.addURI(AUTHORITY,"delete",DELETE);
        sUriMatcher.addURI(AUTHORITY,"update",UPDATE);
    }

    /** 要操作的数据库 */
    private SQLiteDatabase mDatabase;
    @Override
    public boolean onCreate() {

        DbHelper dbHelper = new DbHelper(getContext());
        mDatabase = dbHelper.getWritableDatabase();

        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        int match = sUriMatcher.match(uri);
        if(match == QUERY){

            return mDatabase.query(true, LazyConstant.Database.Tb_PlayList.TB_NAME,projection,selection,selectionArgs,
                            null,null,sortOrder,null);

        }else {
            throw new IllegalArgumentException("IllegalArgumentException");
        }

    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int match = sUriMatcher.match(uri);
        if(match == INSERT){
            mDatabase.insert(LazyConstant.Database.Tb_PlayList.TB_NAME, null, values);
        }else{
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        if(match == DELETE){
            return mDatabase.delete(LazyConstant.Database.Tb_PlayList.TB_NAME,selection,selectionArgs);
        }else{
            throw new IllegalArgumentException("IllegalArgumentException");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        if(match == UPDATE){
            return mDatabase.update(LazyConstant.Database.Tb_PlayList.TB_NAME,values,selection,selectionArgs);
        }else{
            throw new IllegalArgumentException("IllegalArgumentException");
        }
    }
}
