package service;

import model.Director;
import model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        log.info("Add movie started...");
        this.repo.save(movie);
        log.info("Add movie done.");
        return movie;

    }

    @Override
    public void deleteMovie(int id) {
        log.info("Delete movie started...");
        this.repo.deleteById(id);
        log.info("Delete movie done.");
    }
    @Transactional
    @Override
    public Movie updateMovie(Movie movie) {

        log.info("Update movie started...");
        Movie updatedMovie=this.repo.findById(movie.getId()).orElseThrow();
        updatedMovie.setName(movie.getName());
        updatedMovie.setRating(movie.getRating());
        updatedMovie.setYear(movie.getYear());
        log.info("Update movie done.");
        return movie;
    }

    @Override
    public Movie getById(int id) {
        return this.repo.getOne(id);
    }

    @Override
    public Iterable<Movie> getAllMovies() {

        return this.repo.findAllByOrderByIdAsc();
    }

    @Override
    public Iterable<Movie> getMoviesWithRatingGreater(int lower) {

        return this.repo.getMoviesByRatingGreaterThan(lower);
    }

    @Override
    public Double getPercentageOfMoviesThisDecade(int decade)
    {
        int totalMovieCount=(int)this.repo.count();
        int moviesThisDecadeCount=this.repo.countMoviesByYearGreaterThan(decade);
        return (double) ((moviesThisDecadeCount * 100) / totalMovieCount);
    }
}
