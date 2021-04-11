package service;

import model.Movie;
import org.springframework.stereotype.Service;

@Service
public class MovieService implements IMovieService{
    @Override
    public Movie addMovie(Movie movie) {
        return null;
    }

    @Override
    public void deleteMovie(int id) {

    }

    @Override
    public Movie updateMovie(Movie movie) {
        return null;
    }

    @Override
    public Movie getById(int id) {
        return null;
    }

    @Override
    public Iterable<Movie> getAllMovies() {
        return null;
    }

    @Override
    public Iterable<Movie> getMoviesWithRatingGreater(int lower) {
        return null;
    }
}
