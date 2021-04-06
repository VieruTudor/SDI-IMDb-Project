package repository;
import domain.Actor;
import org.springframework.stereotype.Repository;

@Repository
public interface IActorRepo  extends IRepository<Actor, Integer>{
}