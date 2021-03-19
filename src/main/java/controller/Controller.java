package controller;

import domain.*;
import exception.DuplicateException;
import exception.ValidException;
import repository.AbstractRepository;
import repository.IRepository;
import validator.Validator;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// TODO OVI
public class Controller {
    private IRepository<Integer, Actor> actors;
    private IRepository<Integer, Movie> movies;
    private IRepository<Pair<Integer, Integer>, PlaysIn> playsIn;
    private IRepository<Integer, Director> directors;

    /**
     * Parameterized constructor
     *
     * @param actors   given Actors repository
     * @param movies   given Movies repository
     * @param plays_in given PlaysIn repository
     */
    public Controller(IRepository<Integer, Actor> actors, IRepository<Integer, Movie> movies, IRepository<Pair<Integer, Integer>, PlaysIn> plays_in, IRepository<Integer, Director> directors) {
        this.actors = actors;
        this.movies = movies;
        this.playsIn = plays_in;
        this.directors = directors;
    }

    /**
     * Default constructor
     */
    public Controller() {

    }


    /**
     * Creates a movie object from the received parameters and adds it to the repository.
     *
     * @param id     given ID
     * @param name   given Name
     * @param rating given Rating
     * @param year   given Year
     */
    public void addMovie(int id, String name, int rating, int year, int directorId) {
        Validator.validateMovie(name, rating, year, directorId);
        Movie new_movie = new Movie(name, rating, year, directorId);
        new_movie.setId(id);
        Optional.of(this.directors.findOne(directorId)).get().orElseThrow(
                () -> {
                    throw new ValidException("There isn't any director with that id!");
                });
        this.movies.save(new_movie);
    }

    /**
     * Creates an actor object from the received parameters and adds it to the repository.
     *
     * @param id   given ID
     * @param name given Name
     * @param age  given Age
     * @param fame given Fame
     */
    public void addActor(int id, String name, int age, int fame) throws DuplicateException {
        Validator.validateActor(name, age, fame);
        Actor new_actor = new Actor(name, age, fame);
        new_actor.setId(id);
        this.actors.save(new_actor);
    }

    /**
     * Checks for movie and actor to be present in the repository, creates a PlaysIn object if this is fulfilled.
     *
     * @param movieId given movie ID
     * @param actorId given actor ID
     * @param role    - given role
     */
    public void addPlaysIn(int movieId, int actorId, String role) {
        Validator.validatePlaysIn(role);
        Optional.of(this.movies.findOne(movieId)).get().orElseThrow(
                () -> {
                    throw new ValidException("Movie not present in repository!");
                });

        Optional.of(this.actors.findOne(actorId)).get().orElseThrow(
                () -> {
                    throw new ValidException("Actor not present in repository!");
                });

        PlaysIn P = new PlaysIn(movieId, actorId, role);

        this.playsIn.save(P);
    }

    /**
     * Creates a Director object and adds it into the repository
     *
     * @param directorId   the Id of the Director
     * @param directorName name of the Director
     * @param directorAge  age of the Director
     */
    public void addDirector(int directorId, String directorName, int directorAge) {
        Validator.validateDirector(directorName, directorAge);
        Director directorToAdd = new Director(directorName, directorAge);
        directorToAdd.setId(directorId);
        this.directors.save(directorToAdd);
    }

    /**
     * Deletes a movie based on its id
     *
     * @param movieId given Id for the movie to be deleted
     */
    public void deleteMovie(int movieId) {
        this.movies.delete(movieId);
    }

    /**
     * Deletes an actor based on its id
     *
     * @param actorId given Id for the actor to be deleted
     */
    public void deleteActor(Integer actorId) {
        this.actors.delete(actorId);
    }

    /**
     * Deletes a role(playsIn) based on ids of the movie and the actor
     *
     * @param movieId given Id of the movie in which the actor plays
     * @param actorId given Id of the actor which plays in the given movie
     */
    public void deletePlaysIn(Integer movieId, Integer actorId) {
        this.playsIn.delete(new Pair<Integer, Integer>(actorId, movieId));
    }

    /**
     * Deletes a Director based on the given Id
     *
     * @param directorId given Id
     */
    public void deleteDirector(int directorId) {
        this.directors.delete(directorId);
    }

    /// TODO: update for each of them

    /**
     * Updates an actor based on a given ID
     *
     * @param id   the given ID
     * @param name the new name
     * @param age  the new age
     * @param fame the new fame
     */
    public void updateActor(Integer id, String name, int age, int fame) {
        Validator.validateActor(name, age, fame);
        Actor new_actor = new Actor(name, age, fame);
        new_actor.setId(id);
        this.actors.update(new_actor);
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
    public void updateMovie(int id, String name, int rating, int year, int directorId) {
        Optional.of(this.directors.findOne(directorId)).get().orElseThrow(
                () -> {
                    throw new ValidException("There isn't any director with that id!");
                });

        Validator.validateMovie(name, rating, year, directorId);
        Movie new_movie = new Movie(name, rating, year, directorId);
        new_movie.setId(id);
        this.movies.update(new_movie);
    }

    /**
     * Updates a Director based on a given ID
     *
     * @param directorId   the given ID
     * @param directorName the new director name
     * @param directorAge  the new director age
     */
    public void updateDirector(int directorId, String directorName, int directorAge) {
        Validator.validateDirector(directorName, directorAge);
        Director new_director = new Director(directorName, directorAge);
        new_director.setId(directorId);
        this.directors.update(new_director);
    }

    /**
     * Updates a plays_in entity identified by the movieID and the actorID
     *
     * @param movieId the given movie id
     * @param actorId the given actorID
     * @param role    the new role
     */
    public void updatePlaysIn(int movieId, Integer actorId, String role) {
        Validator.validatePlaysIn(role);
        PlaysIn newPlaysIn = new PlaysIn(movieId, actorId, role);
        this.playsIn.update(newPlaysIn);
    }

    /**
     * Gets all the movies in the repository.
     *
     * @return Iterable containing all movies in the repository.
     */

    //update Movie
    //update Actor
    //update PlaysIn
    public Iterable<Movie> getAllMovies() {
        return this.movies.findAll();
    }

    /**
     * Gets all the actors in the repository.
     *
     * @return Iterable containing all actors in the repository.
     */
    public Iterable<Actor> getAllActors() {
        return this.actors.findAll();
    }

    /**
     * Gets all the PlaysIn in the repository.
     *
     * @return Iterable containing all the PlaysIn objects.
     */
    public Iterable<PlaysIn> getAllPlaysIn() {
        return this.playsIn.findAll();
    }

    /**
     * Gets all the directors from the repository.
     *
     * @return Iterable containing all the Director objects.
     */
    public Iterable<Director> getAllDirectors() {
        return this.directors.findAll();
    }

    /**
     * Get all movies with rating higher then given margin
     *
     * @param margin given margin
     * @return Set containing the collection resulted
     */
    public Set<Movie> getMoviesWithRatingHigherThan(int margin) {
        return StreamSupport.stream(this.getAllMovies().spliterator(), false)
                .collect(Collectors.toSet()).stream()
                .filter(m -> m.getRating() >= margin).collect(Collectors.toSet());
    }

    /**
     * Get all actors with fame between given interval
     *
     * @param lower - lower threshold of the interval
     * @param upper - upper threshold of the interval
     * @return Set containing the collection resulted
     */
    public Set<Actor> getActorsWithFameBetween(int lower, int upper) {
        return StreamSupport.stream(this.getAllActors().spliterator(), false)
                .collect(Collectors.toSet()).stream()
                .filter(m -> m.getFame() >= lower && m.getFame() <= upper).collect(Collectors.toSet());
    }

    /**
     * Get all directors with age smaller then given margin
     *
     * @param margin given margin
     * @return Set containing the collection resulted
     */
    public Set<Director> getDirectorsWithAgeSmallerThen(int margin) {
        return StreamSupport.stream(this.getAllDirectors().spliterator(), false)
                .collect(Collectors.toSet()).stream()
                .filter(m -> m.getAge() <= margin).collect(Collectors.toSet());
    }

    /**
     * Get all plays in relations with role equal to given role
     *
     * @param role given role
     * @return Set containing the collection resulted
     */
    public Set<PlaysIn> getPlayInRelationAfterRole(String role) {
        return StreamSupport.stream(this.getAllPlaysIn().spliterator(), false)
                .collect(Collectors.toSet()).stream()
                .filter(m -> m.getRole().equals(role)).collect(Collectors.toSet());
    }

    /**
     * Get percentage of relations with role equal to given role
     *
     * @param role given role
     * @return Long value representing the requested percentage
     */
    public Long getPercentageOfRolesOfActors(String role){
        long actorsWithRequestedRole = StreamSupport.stream(this.getAllPlaysIn().spliterator(), false)
                .collect(Collectors.toSet()).stream()
                .filter(m -> m.getRole().equals(role)).count();
        long allActors = StreamSupport.stream(this.getAllPlaysIn().spliterator(), false).count();
        return (actorsWithRequestedRole * 100) / allActors;
    }

    /**
     * Get percentage of actors that have a fame greater then 60
     *
     * @return Long value representing the requested percentage
     */
    public Long getPercentageOfFamousActors(){
        long famousActors = StreamSupport.stream(this.getAllActors().spliterator(), false)
                .collect(Collectors.toSet()).stream()
                .filter(a -> a.getFame() >= 6)
                .count();
        long allActors = StreamSupport.stream(this.getAllActors().spliterator(), false).count();
        return (famousActors * 100) / allActors;
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

    /**
     * Get percentage of directors that are younger then 28
     *
     * @return Long value representing the requested percentage
     */
    public Long getPercentageOfYoungDirectors(){
        long youngDirectors = StreamSupport.stream(this.getAllDirectors().spliterator(), false)
                .collect(Collectors.toSet()).stream()
                .filter(director -> director.getAge() <= 28)
                .count();
        long allDirectors = StreamSupport.stream(this.getAllDirectors().spliterator(), false).count();
        return (youngDirectors * 100) / allDirectors;
    }

}
