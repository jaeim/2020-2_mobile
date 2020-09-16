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
    public ArrayList<Movie> getAllMovie() {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + MovieDBHelper.TABLE_NAME, null);

        while (cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(MovieDBHelper.COL_ID));
            String movieTitle = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TITLE));
            String releaseDate = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_RELEASE));
            String director = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DIRECTOR));
            String actors = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_ACTORS));
            float rating = cursor.getFloat(cursor.getColumnIndex(MovieDBHelper.COL_RATING));
            String review = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_REVIEW));

            movieList.add(new Movie(id, movieTitle, releaseDate, director, actors, review, rating));
        }

        cursor.close();
        movieDBHelper.close();

        return movieList;
    }

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
    //제목을 기준으로 Movie 검색
    public ArrayList<Movie> getMoviesByTitle(String title) {
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        ArrayList<Movie> selectedMovies = new ArrayList<Movie>();

        String selection = MovieDBHelper.COL_TITLE + "=?";
        String[] selectArgs = new String[] {    title   };

        Cursor cursor = db.query(MovieDBHelper.TABLE_NAME, null, selection, selectArgs, null, null, null, null);
        while (cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(MovieDBHelper.COL_ID));
            String movieTitle = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TITLE));
            String releaseDate = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_RELEASE));
            String director = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DIRECTOR));
            String actors = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_ACTORS));
            float rating = cursor.getFloat(cursor.getColumnIndex(MovieDBHelper.COL_RATING));
            String review = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_REVIEW));

            selectedMovies.add(new Movie(id, movieTitle, releaseDate, director, actors, review, rating));
            cursor.close();
            movieDBHelper.close();
        }
        return selectedMovies;
    }


    //    close 수행
    public void close() {
        if (movieDBHelper != null) movieDBHelper.close();
        if (cursor != null) cursor.close();
    };

}
