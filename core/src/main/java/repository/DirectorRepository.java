package repository;

import model.Director;
import org.springframework.data.jpa.repository.Query;
public interface DirectorRepository extends IRepository<Director, Integer> {

    @Query("select d from director d where d.age < ?1")
    Iterable<Director> getDirectorsAgeSmallerThen(int margin);

    @Query("select count(*) from director d where d.age < ?1")
    int getCountOfYoungDirectors(int age);


}
