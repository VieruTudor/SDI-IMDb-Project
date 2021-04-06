package repository;
import domain.PlaysIn;
import domain.Pair;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlaysInRepo extends IRepository<PlaysIn, Pair<Integer, Integer>>{
}