package repository;

import model.Actor;
import org.springframework.data.jpa.repository.Query;

public interface ActorRepository extends IRepository<Actor, Integer>{
      // This is a version
//    @Query("select u from actor u where u.fame between ?1 and ?2")
//    Iterable<Actor> getActorsFameBetween(int lower, int upper);

      // This is cleaner
      Iterable<Actor> findActorByFameBetween(int lower, int upper);

      int countActorByFameGreaterThanEqual(int threshold);


}
