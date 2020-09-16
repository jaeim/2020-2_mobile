package ddwucom.mobile.finalreport;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etReleaseDate;
    EditText etDirector;
    EditText etActor;
    EditText etRating;
    EditText etReview;


    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        etTitle = findViewById(R.id.et_add_title);
        etReleaseDate = findViewById(R.id.et_add_releaseDate);
        etDirector = findViewById(R.id.et_add_director);
        etActor = findViewById(R.id.et_add_actor);
        etRating = findViewById(R.id.et_add_rating);
        etReview = findViewById(R.id.et_add_review);


        movieDBManager = new MovieDBManager(this);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                String title = etTitle.getText().toString();
                String releaseDate = etReleaseDate.getText().toString();
                String director = etDirector.getText().toString();
                String actor = etActor.getText().toString();
                String rating = etRating.getText().toString();
                String review = etReview.getText().toString();
                //rating 형변환은 Movie 객체 생성시!

                //필수 항목 미기재시 토스트
                if(title.equals("") || releaseDate.equals("") || director.equals("") || actor.equals("") || rating.equals("") || review.equals("")) {
                    Toast.makeText(this, "입력하지 않은 항목이 있습니다.", Toast.LENGTH_SHORT).show();
                }
                else {//Movie 추가 후 mainActivity 복귀
                    Movie newMovie = new Movie(title, releaseDate, director, actor, review, Float.valueOf(rating));
                    //DB에 추가
                    boolean result = movieDBManager.addNewMovie(newMovie);
                    if (result) {
                        Toast.makeText(this,"영화를 추가 하였습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(this, "영화를 추가하지 못했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_add_cancel:
                finish();
                break;
        }

    }


}
