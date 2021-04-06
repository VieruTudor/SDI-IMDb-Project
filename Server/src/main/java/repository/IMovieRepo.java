package repository;

import domain.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepo  extends IRepository<Movie, Integer>{
}