package controller;

import domain.Movie;

import java.util.concurrent.Future;

public interface IMovieController {
    Future<Void> addMovie(int id, String name, int rating, int year, int directorId);

    Future<Void> deleteMovie(int id);

    Future<Void> updateMovie(int id, String name, int rating, int year, int directorId);

    Future<Iterable<Movie>> getAllMovies();

    Future<Iterable<Movie>> getMoviesWithRatingHigherThan(int margin);

    Future<Double> getPercentageOfMoviesThisDecade(int decade);
}
