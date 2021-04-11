package repository;

import model.Movie;
import org.springframework.data.jpa.repository.Query;
public interface MovieRepository extends IRepository<Movie, Integer> {

    Iterable<Movie> findAllByOrderByIdAsc();

    Iterable<Movie> getMoviesByRatingGreaterThan(int margin);

    int countMoviesByYearGreaterThan(int decade);
}
