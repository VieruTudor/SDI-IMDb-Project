package repository;

import model.Actor;
import org.springframework.data.jpa.repository.Query;

public interface ActorRepository extends IRepository<Actor, Integer>{

    @Query("select u from actor u where u.fame between ?1 and ?2")
    Iterable<Actor> getActorsFameBetween(int lower, int upper);



    @Query("select count(*) from actor u where u.fame > ?1")
    int countActorByFameGreaterThanEqual(int threshold);


}
