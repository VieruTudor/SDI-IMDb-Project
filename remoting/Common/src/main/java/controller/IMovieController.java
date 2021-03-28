package controller;

import domain.Movie;

public interface IMovieController {
    void addMovie(int id, String name, int rating, int year, int directorId);

    void deleteMovie(int id);

    void updateMovie(int id, String name, int rating, int year, int directorId);

    Iterable<Movie> getAllMovies();

    Iterable<Movie> getMoviesWithRatingHigherThan(int margin);

    Double getPercentageOfMoviesThisDecade(int decade);

}
