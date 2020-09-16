package ddwucom.mobile.finalreport;

import java.io.Serializable;

public class Movie implements Serializable {
    long _id;
    String movieTitle;
    String releaseDate;//1980-05-23
    String director;
    String actors;//(톰크루즈 외)
    String review;
    float rating;//sqlite에서 real?


    public Movie(String movieTitle, String releaseDate, String director, String actors, float rating) {
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
    }

    public Movie(long _id, String movieTitle, String releaseDate, String director, String actors, float rating) {
        this._id = _id;
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
    }

    public Movie(String movieTitle, String releaseDate, String director, String actors, String review, float rating) {
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.director = director;
        this.actors = actors;
        this.review = review;
        this.rating = rating;
    }

    public Movie(long _id, String movieTitle, String releaseDate, String director, String actors, String review, float rating) {
        this._id = _id;
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.director = director;
        this.actors = actors;
        this.review = review;
        this.rating = rating;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
