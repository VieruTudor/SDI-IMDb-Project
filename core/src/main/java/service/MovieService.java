package service;

import model.Director;
import model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MovieService implements IMovieService{

    @Autowired
    private  MovieRepository repo;

    public static final Logger log = LoggerFactory.getLogger(MovieService.class);
    @Override
    public Movie addMovie(Movie movie) {
        log.trace("Add movie started...");
        this.repo.save(movie);
        log.trace("Add movie done.");
        return movie;

    }

    @Override
    public void deleteMovie(int id) {
        log.trace("Delete movie started...");
        this.repo.deleteById(id);
        log.trace("Delete movie done.");
    }

    @Override
    public Movie updateMovie(Movie movie) {

        log.trace("Update movie started...");
        Movie updatedMovie=this.repo.findById(movie.getId()).orElseThrow();
        updatedMovie.setName(movie.getName());
        updatedMovie.setRating(movie.getAge());
        updatedMovie.setYear(movie.getYear());
        log.trace("Update movie done.");
        return movie;
    }

    @Override
    public Movie getById(int id) {
        return this.repo.getOne(id);
    }

    @Override
    public Iterable<Movie> getAllMovies() {

        return this.repo.findAll();
    }

    @Override
    public Iterable<Movie> getMoviesWithRatingGreater(int lower) {

        return this.repo.getMoviesRatingHigherThan(lower);
    }

    @Override
    public Double getPercentageOfMoviesThisDecade(int decade)
    {
        int totalMovieCount=(int)this.repo.count();
        int moviesThisDecadeCount=this.repo.getCountOfMoviesThisDecade(decade);
        return (double) ((moviesThisDecadeCount * 100) / totalMovieCount);
    }
}
