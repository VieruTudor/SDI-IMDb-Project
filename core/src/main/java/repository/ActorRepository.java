package repository;

import model.Actor;
import org.springframework.data.jpa.repository.Query;

public interface ActorRepository extends IRepository<Actor, Integer>{

    Iterable<Actor> findAllByOrderByIdAsc();

    Iterable<Actor> getActorByFameBetween(int lower, int upper);

    int countActorByFameGreaterThanEqual(int threshold);




}
