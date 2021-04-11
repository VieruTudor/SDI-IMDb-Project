package repository;

import model.Pair;
import model.PlaysIn;
import org.springframework.data.jpa.repository.Query;
public interface PlaysInRepository extends IRepository<PlaysIn, Pair<Integer, Integer>> {

    Iterable<PlaysIn> findAllByOrderByActorIDAscMovieIDAsc();
    Iterable<PlaysIn> getPlaysInByRoleEquals(String role);

    int countPlaysInByRoleEquals(String role);
}
