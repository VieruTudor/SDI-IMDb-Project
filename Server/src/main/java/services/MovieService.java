package services;
import org.springframework.stereotype.Service;
import interfaces.IMovieService;
import repository.IMovieRepo;
import java.util.stream.*;
import domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import exception.*;
import validators.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private IMovieRepo repo;

    public static final Logger log = LoggerFactory.getLogger(MovieService.class);

    /**
     * Creates a movie object from the received parameters and adds it to the repository.
     *
     * @param id     given ID
     * @param name   given Name
     * @param rating given Rating
     * @param year   given Year
     */
    @Override
    @Transactional
    public void addMovie(int id, String name, int rating, int year, int directorId)
    {
        log.info("add movie - method started");
        Validator.validateMovie(name,rating,year,directorId);
        Movie newMovie=new Movie(name,rating,year,directorId);
        newMovie.setId(id);
        this.repo.save(newMovie);
        log.info("add movie - done");
    }

    /**
     * Deletes a movie based on its id
     *
     * @param id given Id for the movie to be deleted
     */
    @Override
    @Transactional
    public void deleteMovie(int id)
    {
        log.info("delete movie - method started");
        Optional.of(this.repo.findById(id)).get().orElseThrow(
                () -> {
                    throw new InexistentEntity("Movie not present !");
                });
        this.repo.deleteById(id);
        log.info("delete movie - done");
    }

    /**
     * Updates a Movie based on a given ID
     *
     * @param id         the specified id
     * @param name       the new name
     * @param rating     the new rating
     * @param year       the new year
     * @param directorId the new directorID (has to be validated)
     */
    @Override
    @Transactional
    public void updateMovie(int id, String name, int rating, int year, int directorId)
    {
        log.info("update movie - method started");
        Validator.validateMovie(name,rating,year,directorId);
        Optional.of(this.repo.findById(id)).get().orElseThrow(
                () -> {
                    throw new InexistentEntity("Movie not present !");
                });

        this.repo.deleteById(id);
        Movie updated=new Movie(name,rating,year,directorId);
        updated.setId(id);
        this.repo.save(updated);
        log.info("update movie - done");

    }

    /**
     * Gets all the movies in the repository.
     *
     * @return Iterable containing all movies in the repository.
     */
    @Override
    public Iterable<Movie> getAllMovies()
    {
        return this.repo.findAll();
    }

    /**
     * Get all movies with rating higher then given margin
     *
     * @param margin given margin
     * @return Set containing the collection resulted
     */
    @Override
    public Iterable<Movie> getMoviesWithRatingHigherThan(int margin)
    {
        return this.repo.findAll().stream()
                .filter(m -> m.getRating() >= margin).collect(Collectors.toSet());
    }

    /**
     * Get percentage of movies that appeared this decade
     *
     * @return Long value representing the requested percentage
     */
    @Override
    public Double getPercentageOfMoviesThisDecade(int decade)
    {
        long thisDecadeMovies=this.repo.findAll().stream()
                .filter(movie -> movie.getYear() >= 2010)
                .count();
        long allMovies=this.repo.count();
        return (double)((thisDecadeMovies*100)/allMovies);
    }
}