package ddwucom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MovieDBHelper extends SQLiteOpenHelper {
    final static String TAG = "MovieDBHelper";

    final static String DB_NAME = "movies.db";
    public final static String TABLE_NAME = "movie_table";

    public final static String COL_ID = "_id";
    public final static String COL_TITLE = "movieTitle";
    public final static String COL_RELEASE = "releaseDate";
    public final static String COL_DIRECTOR = "director";
    public final static String COL_ACTORS = "actors";
    public final static String COL_RATING = "rating";

    public MovieDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " +
                COL_TITLE + " TEXT, " + COL_RELEASE + " TEXT, " + COL_DIRECTOR + " TEXT, " + COL_ACTORS +
                " TEXT, " + COL_RATING + " REAL)";
        Log.d(TAG, sql);
        db.execSQL(sql);

        db.execSQL("insert into " + TABLE_NAME + " values (null, '보이후드', '2014-10-23', '리처드 링클레이터', '에단호크 외', '5.0')");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '라푼젤', '2011-02-10', '바이론 하워드', '맨디무어 외', '4.5')");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '타이타닉', '1998-02-20', '제임스 카메론', '레오나르도 디카프리오 외', '4.0')");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '어바웃 타임', '2013-12-05', '리차드 커티스', '레이첼 맥아덤스 외', '4.0')");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '노트북', '2004-11-26', '닉 카사베츠', '라이언 고슬링 외', '3.0')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
