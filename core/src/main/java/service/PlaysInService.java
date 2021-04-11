package service;

import model.Movie;
import model.Pair;
import model.PlaysIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PlaysInRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PlaysInService implements IPlaysInService{
    @Autowired
    private PlaysInRepository repo;

    public static final Logger log = LoggerFactory.getLogger(PlaysInService.class);

    @Override
    public PlaysIn addPlaysIn(PlaysIn playsIn) {
        log.trace("Add playsIn started...");
        this.repo.save(playsIn);
        log.trace("Plays In added.");
        return playsIn;
    }

    @Override
    public void deletePlaysIn(Pair<Integer, Integer> id) {
        log.trace("Delete playsIn started...");
        this.repo.deleteById(id);
        log.trace("Delete playsIn done.");

    }

    @Override
    public PlaysIn updatePlaysIn(PlaysIn playsIn) {
        log.trace("Update playsIn started...");
        PlaysIn updatedPlaysIn=this.repo.findById(playsIn.getId()).orElseThrow();
        updatedPlaysIn.setRole(playsIn.getRole());
        log.trace("Update playsIn done.");
        return playsIn;
    }

    @Override
    public PlaysIn getById(Pair<Integer, Integer> id) {

        return this.repo.getOne(id);
    }

    @Override
    public Iterable<PlaysIn> getAllPlaysIns() {

//        return this.repo.findAll();
        return this.repo.findAllByOrderByActorIDAscMovieIDAsc(); // this might not work; use query if not
    }

    @Override
    public Iterable<PlaysIn> getAllPlaysInWithRole(String role) {
        return this.repo.getPlaysInByRoleEquals(role);
    }

    @Override
    public Double getPercentageOfActorsWithRole(String role)
    {
        int PlaysInCount=(int)this.repo.count();
        int specificRoleCount=this.repo.countPlaysInByRoleEquals(role);
        return (double) ((specificRoleCount * 100) / PlaysInCount);

    }
}
