package repository;

import model.Pair;
import model.PlaysIn;
import org.springframework.data.jpa.repository.Query;
public interface PlaysInRepository extends IRepository<PlaysIn, Pair<Integer, Integer>> {

    @Query("select p from PlaysIn p where p.role = ?1")
    Iterable<PlaysIn> getPlaysInRelationAfterRole(String role);

    @Query("select count(*) from PlaysIn p where p.role = ?1")
    int getCountPlaysInAfterRole(String role);
}
