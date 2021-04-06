package services;

import interfaces.IPlaysInService;
import repository.IActorRepo;
import repository.IMovieRepo;
import repository.IPlaysInRepo;
import validators.*;
import domain.Pair;
import domain.PlaysIn;
import exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaysInService implements IPlaysInService {

    public static final Logger log = LoggerFactory.getLogger(PlaysInService.class);

    @Autowired
    private IMovieRepo movieRepo;
    @Autowired
    private IActorRepo actorRepo;
    @Autowired
    private IPlaysInRepo playsInRepo;

    /**
     * Checks for movie and actor to be present in the repository, adds new PlaysIn object to its repository
     *
     * @param movieId given movie ID
     * @param actorId given actor ID
     * @param role given role
     */
    @Override
    @Transactional
    public void addPlaysIn(int movieId, int actorId, String role) {
        log.trace("add Plays In - method started");
        Validator.validatePlaysIn(role);
        Optional.of(this.movieRepo.findById(movieId)).get().orElseThrow(
                () -> {
                    throw new ValidException("Movie not present in repository!");
                });

        Optional.of(this.actorRepo.findById(actorId)).get().orElseThrow(
                () -> {
                    throw new ValidException("Actor not present in repository!");
                });

        PlaysIn playsIn = new PlaysIn(movieId, actorId, role);
        this.playsInRepo.save(playsIn);
        log.trace("add PlaysIn - ✔ done");
    }

    /**
     * Deletes a role(playsIn) base on ids of the movie and the actor
     *
     * @param movieId given Id of the movie in which actor plays
     * @param actorId given Id of the actor which plays in the given movie
     */
    @Override
    @Transactional
    public void deletePlaysIn(int movieId, int actorId) {
        log.trace("delete Role - method started");
        this.playsInRepo.deleteById(new Pair<>(movieId, actorId));
        log.trace("delete Role - ✔ done");
    }

    /**
     * Update PlaysIn Object identified by the movieId and the actorId
     *
     * @param movieId given movie id
     * @param actorId given actor id
     * @param role the new role
     */
    @Override
    @Transactional
    public void updatePlaysIn(int movieId, int actorId, String role) {
        log.trace("update Role - method started");
        Validator.validatePlaysIn(role);
        Optional.of(this.playsInRepo.findById(new Pair<>(actorId, movieId))).get().orElseThrow(
                () -> {
                    throw new InexistentEntity("Role not present");
                });
        this.playsInRepo.deleteById(new Pair<>(actorId, movieId));
        PlaysIn newPlaysIn = new PlaysIn(movieId, actorId, role);
        this.playsInRepo.save(newPlaysIn);
        log.trace("update Role - ✔ done");
    }

    /**
     * Gets all the Roles from the its repository
     *
     * @return Iterable containing all the PlaysIn objects
     */
    @Override
    public Iterable<PlaysIn> getAllPlaysIn() {
        return this.playsInRepo.findAll();
    }

    /**
     * Gets all PlaysIn in relations with the role equal with a given role
     *
     * @param role given role
     * @return Set containing the collection resulted
     */
    @Override
    public Iterable<PlaysIn> getPlaysInRelationAfterRole(String role) {
        return this.playsInRepo.findAll()
                .stream()
                .filter(r -> r.getRole().equals(role))
                .collect(Collectors.toSet());
    }

    /**
     * Gets percentage of relations with role equal to given role
     *
     * @param role given role
     * @return Double value representing the requested percentage
     */
    @Override
    public Double getPercentageOfRolesOfActors(String role) {
        double actorsWithRequestedRole = this.playsInRepo.findAll()
                .stream()
                .filter(actorRole -> actorRole.getRole().equals(role))
                .count();
        double allRoles = this.playsInRepo.count();
        return (actorsWithRequestedRole * 100) / allRoles;
    }
}