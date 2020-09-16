package ddwucom.mobile.finalreport;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etReleaseDate;
    EditText etDirector;
    EditText etActor;
    EditText etRating;
    EditText etReview;
    ImageView poster;

    MovieDBManager movieDBManager;
    Movie movie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);

        movie = (Movie) getIntent().getSerializableExtra("movie");

        movieDBManager = new MovieDBManager(this);

        etTitle = findViewById(R.id.et_update_title);
        etReleaseDate = findViewById(R.id.et_update_releaseDate);
        etDirector = findViewById(R.id.et_update_director);
        etActor = findViewById(R.id.et_update_actor);
        etRating = findViewById(R.id.et_update_rating);
        etReview = findViewById(R.id.et_update_review);

        etTitle.setText(movie.getMovieTitle());
        etReleaseDate.setText(movie.getReleaseDate());
        etDirector.setText(movie.getDirector());
        etActor.setText(movie.getActors());
        etRating.setText(String.valueOf(movie.getRating()));
        etReview.setText(movie.getReview());

        poster = findViewById(R.id.update_poster);

        switch (movie.getMovieTitle()) {
            case "보이후드" :
                poster.setImageResource(R.mipmap.boyhood);
                break;
            case "타이타닉" :
                poster.setImageResource(R.mipmap.titanic);
                break;
            case "라푼젤" :
                poster.setImageResource(R.mipmap.tangled);
                break;
            case "어바웃 타임" :
                poster.setImageResource(R.mipmap.abouttime);
                break;
            case "노트북" :
                poster.setImageResource(R.mipmap.notebook);
                break;
            default:
                poster.setImageResource(R.mipmap.ic_launcher_round);
                break;
            }
        }

        public void onClick (View view) {
            switch(view.getId()) {
                case R.id.btn_update:

                    String title = etTitle.getText().toString();
                    String releaseDate = etReleaseDate.getText().toString();
                    String director = etDirector.getText().toString();
                    String actor = etActor.getText().toString();
                    String rating = etRating.getText().toString();
                    String review = etReview.getText().toString();
                    //rating 형변환은 Movie 객체 수정시!

                    //필수 항목 미기재시 토스트
                    if(title.equals("") || releaseDate.equals("") || director.equals("") || actor.equals("") || rating.equals("") || review.equals("")) {
                        Toast.makeText(this, "입력하지 않은 항목이 있습니다.", Toast.LENGTH_SHORT).show();
                    }
                    else {//Movie 수정 후 mainActivity 복귀
                        movie.setMovieTitle(title);
                        movie.setReleaseDate(releaseDate);
                        movie.setDirector(director);
                        movie.setActors(actor);
                        movie.setRating(Float.valueOf(rating));
                        movie.setReview(review);

                        //DB 수정
                        if (movieDBManager.modifyMovie(movie)) {
                            Toast.makeText(this,"영화를 수정 하였습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(this, "영화를 수정하지 못했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }


                    break;
                case R.id.btn_update_cancel:
                    finish();
                    break;
            }

        }

}

