package controllers;

import domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class MovieController implements FutureMovieController {

    @Autowired
    private ExecutorService executorService;


    @Override
    public Future<Void> addMovie(int id, String name, int rating, int year, int directorId) {
        return null;
    }

    @Override
    public Future<Void> deleteMovie(int id) {
        return null;
    }

    @Override
    public Future<Void> updateMovie(int id, String name, int rating, int year, int directorId) {
        return null;
    }

    @Override
    public Future<Iterable<Movie>> getAllMovies() {
        return null;
    }

    @Override
    public Future<Iterable<Movie>> getMoviesWithRatingHigherThan(int margin) {
        return null;
    }

    @Override
    public Future<Double> getPercentageOfMoviesThisDecade(int decade) {
        return null;
    }
}
