package controllers;

import domain.Movie;
import interfaces.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

@Service
public class MovieController implements FutureMovieController {


    @Autowired
    private IMovieService movieController;

    @Override
    public void addMovie(int id, String name, int rating, int year, int directorId) {
        movieController.addMovie(id, name, rating, year, directorId);
    }

    @Override
    public void deleteMovie(int id) {
        movieController.deleteMovie(id);
    }

    @Override
    public void updateMovie(int id, String name, int rating, int year, int directorId) {
        movieController.updateMovie(id, name, rating, year, directorId);
    }

    @Override
    public Iterable<Movie> getAllMovies() {
        return movieController.getAllMovies();
    }

    @Override
    public Iterable<Movie> getMoviesWithRatingHigherThan(int margin) {
        return movieController.getMoviesWithRatingHigherThan(margin);
    }

    @Override
    public Double getPercentageOfMoviesThisDecade(int decade) {
        return movieController.getPercentageOfMoviesThisDecade(decade);
    }
}
