package controller;

import domain.Movie;

import java.util.Set;

public interface IMovieController {
    void addMovie(int id, String name, int rating, int year, int directorId);

    void deleteMovie(int id);

    void updateMovie(int id, String name, int rating, int year, int directorId);

    Iterable<Movie> getAllMovies();

    Set<Movie> getMoviesWithRatingHigherThan(int margin);

}
