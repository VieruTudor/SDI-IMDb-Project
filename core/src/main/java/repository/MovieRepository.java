package repository;

import model.Movie;
import org.springframework.data.jpa.repository.Query;
public interface MovieRepository extends IRepository<Movie, Integer> {

    @Query("select m from movie m where m.rating > ?1")
    Iterable<Movie> getMoviesRatingHigherThan(int margin);

    @Query("select count(*) from movie m where m.year >= ?1")
    int getCountOfMoviesThisDecade(int decade);
}
