package ddwucom.mobile.finalreport;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    Button btn_search;
    EditText etSearch;
    private MyCursorAdapter myCursorAdapter;
    ListView searchListView;
    MovieDBManager movieDBManager;
    MovieDBHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        helper = new MovieDBHelper(this);

        btn_search = findViewById(R.id.btn_search);
        etSearch = findViewById(R.id.et_search);

        myCursorAdapter = new MyCursorAdapter(this, R.layout.movie_layout, null);

        searchListView = findViewById(R.id.searchListView);
        searchListView.setAdapter(myCursorAdapter);

        movieDBManager = new MovieDBManager(this);

    }

    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                String keyword = etSearch.getText().toString();

                if (keyword.equals("")) {
                    Toast.makeText(this, "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                }
                else {//검색작업 수행
                    SQLiteDatabase db = helper.getReadableDatabase();

                    String selection = MovieDBHelper.COL_TITLE + "=?";
                    String[] selectArgs = new String[] {    keyword   };

                    Cursor cursor = db.query(MovieDBHelper.TABLE_NAME, null, selection, selectArgs, null, null, null, null);

                    if(cursor != null) {
                        myCursorAdapter.changeCursor(cursor);
                        helper.close();
                    }
                    else {
                        Toast.makeText(this, "검색결과를 찾지 못했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_search_cancel:
                finish();
                break;
        }


    }
}
