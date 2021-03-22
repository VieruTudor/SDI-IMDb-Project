package controllers;

import controller.IMovieController;
import repository.*;
import domain.*;
import java.util.stream.*;
import java.util.Set;
import Validators.Validator;
public class MovieController implements IMovieController {
    private IRepository<Integer,Movie> movies;
    public MovieController(IRepository<Integer,Movie>movies)
    {
        this.movies=movies;
    }
    /**
     * Creates a movie object from the received parameters and adds it to the repository.
     *
     * @param id     given ID
     * @param name   given Name
     * @param rating given Rating
     * @param year   given Year
     */
    @Override
    public void addMovie(int id, String name, int rating, int year, int directorId)
    {
        Validator.validateMovie(name,rating,year,directorId);
        Movie newMovie=new Movie(name,rating,year,directorId);
        newMovie.setId(id);
        this.movies.save(newMovie);
    }
    /**
     * Deletes a movie based on its id
     *
     * @param id given Id for the movie to be deleted
     */
    @Override
    public void deleteMovie(int id)
    {
        this.movies.delete(id);
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
    public void updateMovie(int id, String name, int rating, int year, int directorId)
    {
        Validator.validateMovie(name,rating,year,directorId);
        Movie movie=new Movie(name,rating,year,directorId);
        movie.setId(id);
        this.movies.update(movie);
    }
    /**
     * Gets all the movies in the repository.
     *
     * @return Iterable containing all movies in the repository.
     */
    @Override
    public Iterable<Movie> getAllMovies()
    {
        return this.movies.findAll();
    }
    /**
     * Get all movies with rating higher then given margin
     *
     * @param margin given margin
     * @return Set containing the collection resulted
     */
    @Override
    public Set<Movie> getMoviesWithRatingHigherThan(int margin)
    {
        return StreamSupport.stream(this.getAllMovies().spliterator(), false)
                .collect(Collectors.toSet()).stream()
                .filter(m -> m.getRating() >= margin).collect(Collectors.toSet());
    }
    /**
     * Get percentage of movies that appeared this decade
     *
     * @return Long value representing the requested percentage
     */
    public Long getPercentageOfMoviesThisDecade(){
        long thisDecadeMovies = StreamSupport.stream(this.getAllMovies().spliterator(), false)
                .collect(Collectors.toSet()).stream()
                .filter(movie -> movie.getYear() >= 2010)
                .count();
        long allMovies = StreamSupport.stream(this.getAllMovies().spliterator(), false).count();
        return (thisDecadeMovies * 100) / allMovies;
    }
}
