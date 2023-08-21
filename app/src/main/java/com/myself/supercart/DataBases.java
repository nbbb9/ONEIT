package com.myself.supercart;

import android.provider.BaseColumns;

public final class DataBases {

    public static final class CreateDB implements BaseColumns{

        public static final String NAME = "name";
        public static final String PRICE = "price";
        public static final String WEIGHT = "weight";
        public static final String _TABLENAME0 = "producttable";//테이블 명칭
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                //ID는 정수형태로 생성되는 구분자 테이블 내에서 구분하기 위해 중복되는 값을 생성하지 않도록 행을 추가할 때마다 1씩 증가하여 "_ID"에 입력 됨.
                +NAME+" text not null, "
                +PRICE+" integer not null, "
                +WEIGHT+" integer not null );";

    }

}