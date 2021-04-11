package service;

import model.Movie;

public interface IMovieService {
    Movie addMovie(Movie movie);

    void deleteMovie(int id);

    Movie updateMovie(Movie movie);

    Movie getById(int id);

    Iterable<Movie> getAllMovies();

    Iterable<Movie> getMoviesWithRatingGreater(int lower);

    Double getPercentageOfMoviesThisDecade(int decade);
}
