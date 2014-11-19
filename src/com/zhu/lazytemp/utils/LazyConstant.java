package com.zhu.lazytemp.utils;

/**
 * Created by zhudeshuai on 2014/11/15.
 */
public class LazyConstant {


    /**
     * 存储数据库相关信息
     */
    public static class Database {
        public static final String DB_NAME = "lazy_db";

        /**
         * 播放列表
         */
        public static class Tb_PlayList {
            public static final String TB_NAME = "playlist";
            public static class Field {
                public static final String _ID = "_id";
                public static final String FD_ID = "id";
                public static final String FD_TITLE = "title";
                public static final String FD_ALBUM = "album";
                public static final String FD_ARTIST = "artist";
                public static final String FD_URL = "url";
                public static final String FD_DURATION = "duration";
                public static final String FD_SIZE = "size";
            }
        }
    }

}
