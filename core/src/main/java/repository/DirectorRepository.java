package repository;

import model.Director;
import org.springframework.data.jpa.repository.Query;
public interface DirectorRepository extends IRepository<Director, Integer> {
    Iterable<Director> findAllByOrderByIdAsc();

    Iterable<Director> getDirectorsByAgeLessThan(int margin);

    int countDirectorByAgeLessThanEqual(int age);


}
