package service;

import model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ActorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ActorServiceImpl implements IActorService {

    @Autowired
    private ActorRepository repo;

    public static final Logger log = LoggerFactory.getLogger(ActorServiceImpl.class);


    @Override
    public Actor addActor(Actor actor) {
        log.trace("Add actor started...");
        repo.save(actor);
        log.trace("Add actor done!");
        return actor;
    }

    @Override
    public void deleteActor(int id) {
        log.trace("Delete actor started...");
        repo.deleteById(id);
        log.trace("Delete actor done.");
    }

    @Transactional
    @Override
    public Actor updateActor(Actor actor) {
        log.trace("Update actor started...");
        Actor updateActor = repo.findById(actor.getId()).orElseThrow();
        updateActor.setName(actor.getName());
        updateActor.setAge(actor.getAge());
        updateActor.setFame(actor.getFame());
        log.trace("Update actor done.");
        return actor;
    }

    @Override
    public Actor getById(int id) {
        return repo.getOne(id);
    }

    @Override
    public Iterable<Actor> getAllActors() {
        return repo.findAllByOrderByIdAsc();
    }

    @Override
    public Iterable<Actor> getActorsWithFameBetween(int lower, int upper) {

        return repo.getActorByFameBetween(lower, upper);
    }

    @Override
    public Double getPercentageOfFamousActors(int fame) {
        var count = repo.countActorByFameGreaterThanEqual(fame);
        var totalCount = repo.findAll().size();
        return (double) ((count * 100) / totalCount);

    }
}