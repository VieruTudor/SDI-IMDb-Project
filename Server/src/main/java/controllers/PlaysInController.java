package controllers;

import interfaces.IPlaysInController;
import repository.*;
import domain.*;

import java.util.stream.*;

import validators.Validator;

public class PlaysInController implements IPlaysInController {
    private IRepository<Pair<Integer, Integer>, PlaysIn> playsIn;

    public PlaysInController(IRepository<Pair<Integer, Integer>, PlaysIn> playsIn) {
        this.playsIn = playsIn;
    }

    /**
     * Checks for movie and actor to be present in the repository, creates a PlaysIn object if this is fulfilled.
     *
     * @param movieId given movie ID
     * @param actorId given actor ID
     * @param role    - given role
     */
    @Override
    public void addPlaysIn(int movieId, int actorId, String role) {
        Validator.validatePlaysIn(role);
        PlaysIn newPlaysIn = new PlaysIn(movieId, actorId, role);
        this.playsIn.save(newPlaysIn);
    }

    /**
     * Deletes a role(playsIn) based on ids of the movie and the actor
     *
     * @param movieId given Id of the movie in which the actor plays
     * @param actorId given Id of the actor which plays in the given movie
     */
    @Override
    public void deletePlaysIn(int movieId, int actorId) {
        this.playsIn.delete(new Pair<Integer, Integer>(actorId, movieId));
    }

    /**
     * Updates a plays_in entity identified by the movieID and the actorID
     *
     * @param movieId the given movie id
     * @param actorId the given actorID
     * @param role    the new role
     */
    @Override
    public void updatePlaysIn(int movieId, int actorId, String role) {
        Validator.validatePlaysIn(role);
        PlaysIn newPlaysIn = new PlaysIn(movieId, actorId, role);
        this.playsIn.update(newPlaysIn);
    }

    /**
     * Gets all the PlaysIn in the repository.
     *
     * @return Iterable containing all the PlaysIn objects.
     */
    @Override
    public Iterable<PlaysIn> getAllPlaysIn() {
        return this.playsIn.findAll();
    }

    /**
     * Get all plays in relations with role equal to given role
     *
     * @param role given role
     * @return Set containing the collection resulted
     */
    @Override
    public Iterable<PlaysIn> getPlaysInRelationAfterRole(String role) {
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
    public Double getPercentageOfRolesOfActors(String role) {
        double actorsWithRequestedRole = StreamSupport.stream(this.getAllPlaysIn().spliterator(), false)
                .collect(Collectors.toSet()).stream()
                .filter(m -> m.getRole().equals(role)).count();
        double allActors = StreamSupport.stream(this.getAllPlaysIn().spliterator(), false).count();
        return (actorsWithRequestedRole * 100) / allActors;
    }

}
