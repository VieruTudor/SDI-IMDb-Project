package service;

import model.Actor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ActorRepository;
import validators.Validator;

@Service
public class ActorServiceImpl implements IActorService {

    @Autowired
    private ActorRepository repo;


    @Override
    public Actor addActor(Actor actor) {
        return repo.save(actor);
    }

    @Override
    public void deleteActor(int id) {
        repo.deleteById(id);
    }

    @Override
    public Actor updateActor(Actor actor) {
        Actor updateActor = repo.findById(actor.getId()).orElseThrow();
        updateActor.setName(actor.getName());
        updateActor.setAge(actor.getAge());
        updateActor.setFame(actor.getFame());
        return actor;
    }

    @Override
    public Iterable<Actor> getAllActors() {
        return repo.findAll();
    }

    @Override
    public Iterable<Actor> getActorsWithFameBetween(int lower, int upper) {

        return repo.findActorByFameBetween(lower, upper);
    }

    @Override
    public Double getPercentageOfFamousActors(int fame) {
        var count = repo.countActorByFameGreaterThanEqual(fame);
        var totalCount = repo.findAll().size();
        return (double) ((count * 100) / totalCount);

    }
}