package com.zeeshan.pseudo.popularmovies;

/**
 * Created by Administrator on 9/27/2016.
 */
class MoviePOJO {
    public final String title;
    public final String url;
    public final String orig_title;
    public final String movie_plot;
    public final String movie_rating;
    public final String release_date;

    MoviePOJO(String Title, String URL, String Orig_title, String Movie_plot, String Movie_rating, String Release_date) {
        this.title = Title;
        this.url = URL;
        this.orig_title = Orig_title;
        this.movie_plot = Movie_plot;
        this.movie_rating = Movie_rating;
        this.release_date = Release_date;
    }
}