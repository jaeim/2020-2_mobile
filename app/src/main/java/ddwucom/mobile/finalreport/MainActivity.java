package ddwucom.mobile.finalreport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
//과제명: 영화 관림 기록 앱
//분반: 02분반
//학번: 20170942 성명:송재임
//제출일:2020년 09월 17일
/*
가산점 기능 구현
1)커스텀 Cursor Adapter 적용
(한 항목에 3개 이상 보이도록 구현하였음)
*/

//CursorAdapter 사용
public class MainActivity extends AppCompatActivity {

    final static int UPDATE_ACTIVITY_CODE = 100;
    final static int ADD_ACTIVITY_CODE = 200;

    private ListView listView;
    private MyCursorAdapter myCursorAdapter;
    Cursor cursor;
    MovieDBManager movieDBManager;
    MovieDBHelper helper;
    //cursorChanged == false -> 커서를 변경해야 할 때!
    Boolean cursorChanged;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new MovieDBHelper(this);

        listView = (ListView) findViewById(R.id.myListView);

        myCursorAdapter = new MyCursorAdapter(this, R.layout.movie_layout, null);

        listView.setAdapter(myCursorAdapter);

        movieDBManager = new MovieDBManager(this);

        cursorChanged = false;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //방법1)_id를 사용하여 dto생성 -> intent로 넘기기..
                Cursor c = (Cursor) myCursorAdapter.getItem(position);
                String movieTitle = c.getString(c.getColumnIndex(MovieDBHelper.COL_TITLE));
                String releaseDate = c.getString(c.getColumnIndex(MovieDBHelper.COL_RELEASE));
                String director = c.getString(c.getColumnIndex(MovieDBHelper.COL_DIRECTOR));
                String actors = c.getString(c.getColumnIndex(MovieDBHelper.COL_ACTORS));
                String review = c.getString(c.getColumnIndex(MovieDBHelper.COL_REVIEW));
                float rating = c.getFloat(c.getColumnIndex(MovieDBHelper.COL_RATING));

                Movie movie = new Movie(id, movieTitle, releaseDate, director, actors, review, rating);

                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("movie", movie);
                startActivityForResult(intent, UPDATE_ACTIVITY_CODE);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = (Cursor) myCursorAdapter.getItem(position);
                final long _id = id;

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("영화 삭제")
                        .setMessage("영화 " + c.getString(c.getColumnIndex(MovieDBHelper.COL_TITLE)) + "를(을) 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (movieDBManager.removeMovie(_id)) {
                                    Toast.makeText(MainActivity.this, "삭제하였습니다.", Toast.LENGTH_SHORT).show();
                                    changeCursorToGetAllMovie();
                                }
                                else {  Toast.makeText(MainActivity.this, "삭제하지 못했습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }})
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, ADD_ACTIVITY_CODE);
                break;
            case R.id.menu_intro:
                Intent intro_intent = new Intent(this, IntroductionActivity.class);
                startActivity(intro_intent);
                break;
            case R.id.menu_search:
                Intent search_intent = new Intent(this, SearchActivity.class);
                startActivity(search_intent);
                break;
            case R.id.menu_exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("앱 종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
//                                System.exit(0);
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
                break;
        }

        return true;
    }

    @Override
    //onResume 보다 빨리 실행되는 메소드
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case UPDATE_ACTIVITY_CODE:
            case ADD_ACTIVITY_CODE:
                if (resultCode == RESULT_OK) {
                    cursorChanged = false;
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(cursorChanged == false) {
            changeCursorToGetAllMovie();
        }
    }

//changecursor하는 메소드 따로 생성

    protected void changeCursorToGetAllMovie() {
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from " + MovieDBHelper.TABLE_NAME, null);
        myCursorAdapter.changeCursor(cursor);
        cursorChanged = true;//커서를 변경했으니 더 이상 필요 없다는 뜻
        helper.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cursor != null) cursor.close();
    }
}