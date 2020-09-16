package ddwucom.mobile.finalreport;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

//과제명: 영화 정보 관리 앱
//분반: 02분반
//학번: 20170942 성명:송재임
//제출일:2020년 06월 26일
/*
추가 기능 구현
1)검색 기능: 영화 제목을 기준으로 해당하는 영화 검색
2)위젯 사용: RatingBar 위젯을 사용하여 영화 평점을 기록
3)리스트 뷰 항목에 이미지 사용
*/


public class SearchActivity extends AppCompatActivity {
    Button btn_search;
    EditText etSearch;

    MovieAdapter movieAdapter;
    ArrayList<Movie> searchedMovieList;
    ListView searchListView;
    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        btn_search = findViewById(R.id.btn_search);
        etSearch = findViewById(R.id.et_search);

        searchedMovieList = new ArrayList<Movie>();
        movieAdapter = new MovieAdapter(this, R.layout.movie_layout, searchedMovieList);
        searchListView = findViewById(R.id.searchListView);
        searchListView.setAdapter(movieAdapter);

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
                    if(!movieDBManager.getMoviesByTitle(keyword).isEmpty()) {
                        searchedMovieList.clear();
                        searchedMovieList.addAll(movieDBManager.getMoviesByTitle(keyword));
                        movieAdapter.notifyDataSetChanged();
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
