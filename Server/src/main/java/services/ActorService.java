package services;

import org.springframework.stereotype.Service;
import interfaces.IActorService;
import repository.IActorRepo;
import domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import exception.*;

import java.util.Optional;
import java.util.stream.*;

import validators.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

@Service
public class ActorService implements IActorService {

    @Autowired
    private IActorRepo repo;

    public static final Logger log = LoggerFactory.getLogger(ActorService.class);


    /**
     * Creates an actor object from the received parameters and adds it to the repository.
     *
     * @param id   given ID
     * @param name given Name
     * @param age  given Age
     * @param fame given Fame
     */
    @Override
    @Transactional
    public void addActor(int id, String name, int age, int fame) {
        log.info("add actor - method started");
        Validator.validateActor(name, age, fame);
        Actor newActor = new Actor(name, age, fame);
        newActor.setId(id);
        this.repo.save(newActor);
        log.info("add actor - done");
    }

    /**
     * Deletes an actor based on its id
     *
     * @param id given Id for the actor to be deleted
     */
    @Override
    @Transactional
    public void deleteActor(int id) {
        log.info("delete actor - method started");
        Optional.of(this.repo.findById(id)).get().orElseThrow(
                () -> {
                    throw new InexistentEntity("Movie not present !");
                });
        this.repo.deleteById(id);
        log.info("delete actor - done");
    }

    /**
     * Updates an actor based on a given ID
     *
     * @param id   the given ID
     * @param name the new name
     * @param age  the new age
     * @param fame the new fame
     */
    @Override
    @Transactional
    public void updateActor(int id, String name, int age, int fame) {
        log.info("update actor - method started");
        Validator.validateActor(name, age, fame);
        Optional.of(this.repo.findById(id)).get().orElseThrow(
                () -> {
                    throw new InexistentEntity("Movie not present !");
                });
        this.repo.deleteById(id);
        Actor newActor = new Actor(name, age, fame);
        newActor.setId(id);
        this.repo.save(newActor);
        log.info("update actor - done");


    }

    /**
     * Gets all the actors in the repository.
     *
     * @return Iterable containing all actors in the repository.
     */
    @Override
    public Iterable<Actor> getAllActors() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return this.repo.findAll();
    }

    /**
     * Get all actors with fame between given interval
     *
     * @param lower - lower threshold of the interval
     * @param upper - upper threshold of the interval
     * @return Set containing the collection resulted
     */
    @Override
    public Iterable<Actor> getActorsWithFameBetween(int lower, int upper) {
        var actors = this.repo.findAll().stream().filter(m -> m.getFame() >= lower && m.getFame() <= upper).collect(Collectors.toSet());
        return actors;
    }

    /**
     * Get percentage of actors that have a fame greater then 60
     *
     * @return Long value representing the requested percentage
     */
    @Override
    public Double getPercentageOfFamousActors(int fame) {
        var count = this.repo.findAll().stream().filter(a -> a.getFame() >= fame)
                .count();
        var total = this.repo.count();
        return (double) ((count * 100) / total);

    }

}