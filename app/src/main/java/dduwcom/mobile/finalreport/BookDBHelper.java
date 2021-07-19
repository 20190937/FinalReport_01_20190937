package dduwcom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BookDBHelper extends SQLiteOpenHelper {
    final static String TAG = "BookDBHelper";

    final static String DB_NAME = "books.db";
    public final static String TABLE_NAME = "book_table";

    public final static String COL_ID = "_id";
    public final static String COL_TITLE = "title";
    public final static String COL_AUTHOR = "author";
    public final static String COL_PUBLISHER = "publisher";
    public final static String COL_CONTENTS = "contents";
    public final static String COL_DATE = "date";

    public BookDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " +
                COL_TITLE + " TEXT, " + COL_AUTHOR + " TEXT, " +  COL_PUBLISHER + " TEXT, " +
                COL_CONTENTS + " TEXT, " + COL_DATE + " TEXT)";
        Log.d(TAG, sql);
        db.execSQL(sql);

        // 샘플 데이터가 필요할 때 onCreate() 의  테이블 생성 후 마지막 부분에 추가
        db.execSQL("insert into " + TABLE_NAME + " values (null, '백설공주에게 죽음을', '넬레 노이하우스', '북로드', '추악한 인간의 본성과 이기적임을 재밌게 풀어낸 추리소설', '2021/05/21');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '나미야 잡화점의 기적', '히가시노 게이고', '현대문학', '과거와 현재를 넘나들며 인간의 따뜻함을 보여주는 추리소설', '2021/05/22');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '자존감 수업', '윤홍균', '심플라이프', '자존감을 키우는 방법에 대해 경험적으로 서술되있는 자기계발서', '2021/05/23');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '라플라스의 마녀', '히가시노 게이고', '현대문학', '물리적이지만 꽤나 감성적인 SF 추리소설', '2021/05/24');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '무서운 그림', '나카노 교코', '세미콜론', '어렵게만 느껴지는 예술 작품에 대한 유머러스한 해석을 보여주는 교양도서', '2021/05/25');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
