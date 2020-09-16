package ddwucom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MovieDBManager {

    MovieDBHelper movieDBHelper = null;
    Cursor cursor = null;

    public MovieDBManager(Context context) {    movieDBHelper = new MovieDBHelper(context);    }

    //모든 movie 반환
    /*
    public Cursor getAllMovie() {
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + MovieDBHelper.TABLE_NAME, null);

        //cursor는 닫으면 안됨!
        //Helper도 닫으니까 오류남!
        //해당 movieDBHelper를 사용하는 cursor를 보내는거니까 helper도 닫으면 안되는건가?
       //그냥 cursor에 바로 쿼리를 넣는 것으로 수정하였음

//        movieDBHelper.close();

        return cursor;
    }
*/
    //Movie 추가
    public boolean addNewMovie(Movie newMovie) {
        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues row = new ContentValues();

        row.put(MovieDBHelper.COL_TITLE, newMovie.getMovieTitle());
        row.put(MovieDBHelper.COL_RELEASE, newMovie.getReleaseDate());
        row.put(MovieDBHelper.COL_DIRECTOR, newMovie.getDirector());
        row.put(MovieDBHelper.COL_ACTORS, newMovie.getActors());
        row.put(MovieDBHelper.COL_RATING, newMovie.getRating());
        row.put(MovieDBHelper.COL_REVIEW, newMovie.getReview());

        long count = db.insert(MovieDBHelper.TABLE_NAME, null, row);
        movieDBHelper.close();

        if (count > 0) return true;
        return false;
    }

    //Movie 수정 (id를 기준으로 수정)
    public boolean modifyMovie(Movie movie) {
        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues row = new ContentValues();

        row.put(MovieDBHelper.COL_TITLE, movie.getMovieTitle());
        row.put(MovieDBHelper.COL_RELEASE, movie.getReleaseDate());
        row.put(MovieDBHelper.COL_DIRECTOR, movie.getDirector());
        row.put(MovieDBHelper.COL_ACTORS, movie.getActors());
        row.put(MovieDBHelper.COL_RATING, movie.getRating());
        row.put(MovieDBHelper.COL_REVIEW, movie.getReview());
        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] {    String.valueOf(movie.get_id())   };

        long count = db.update(MovieDBHelper.TABLE_NAME, row, whereClause, whereArgs);
        movieDBHelper.close();

        if(count > 0) return true;
        return false;
    }

    //Movie 삭제 (id를 기준으로 삭제)
    public boolean removeMovie(long id) {
        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };

        long count = db.delete(MovieDBHelper.TABLE_NAME, whereClause, whereArgs);
        movieDBHelper.close();

        if (count > 0) return true;
        return false;
    }

    /*
    //제목을 기준으로 Movie 검색
    public Cursor getMoviesByTitle(String title) {
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();

        String selection = MovieDBHelper.COL_TITLE + "=?";
        String[] selectArgs = new String[] {    title   };

        Cursor cursor = db.query(MovieDBHelper.TABLE_NAME, null, selection, selectArgs, null, null, null, null);

//        movieDBHelper.close();
        return cursor;
    }
*/
    //    close 수행
    public void close() {
        if (movieDBHelper != null) movieDBHelper.close();
        if (cursor != null) cursor.close();
    };

}
