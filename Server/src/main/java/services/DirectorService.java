package services;

import exception.InexistentEntity;
import interfaces.IDirectorService;
import repository.IDirectorRepo;
import validators.*;
import domain.Director;
;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DirectorService implements IDirectorService {

    public static final Logger log = LoggerFactory.getLogger(DirectorService.class);

    @Autowired
    private IDirectorRepo directorRepo;

    /**
     * Creates a Director object and adds it into its repository
     *
     * @param id   the id of the Director
     * @param name name of the Director
     * @param age  age of the Director
     */
    @Override
    @Transactional
    public void addDirector(int id, String name, int age) {
        log.trace("add Director - method started");
        Validator.validateDirector(name, age);
        Director directorToAdd = new Director(name, age);
        directorToAdd.setId(id);
        directorRepo.save(directorToAdd);
        log.trace("add Director - ✔ done");
    }

    /**
     * Deletes a Director based on its id
     *
     * @param id given id
     */
    @Override
    @Transactional
    public void deleteDirector(int id) {
        log.trace("delete Director - method started");
        Optional.of(this.directorRepo.findById(id)).get().orElseThrow(
                () -> {
                    throw new InexistentEntity("Director not present !");
                });
        this.directorRepo.deleteById(id);
        log.trace("delete Director - ✔ done");
    }

    /**
     * Updates a Director based on a given ID
     *
     * @param id   the given ID
     * @param name the new Director name
     * @param age  the new Director age
     */
    @Override
    @Transactional
    public void updateDirector(int id, String name, int age) {
        log.trace("update Director - method started");
        Validator.validateDirector(name, age);
        Optional.of(this.directorRepo.findById(id)).get().orElseThrow(
                () -> {
                    throw new InexistentEntity("Movie not present !");
                });
        this.directorRepo.deleteById(id);
        Director new_director = new Director(name, age);
        this.directorRepo.save(new_director);
        log.trace("update Director - ✔ done");
    }

    /**
     * Get all directors from its repository
     *
     * @return Iterable containing all Director objects
     */
    @Override
    public Iterable<Director> getAllDirectors() {
        return this.directorRepo.findAll();
    }

    /**
     * Get all directors with age smaller then given margin
     *
     * @param margin given margin
     * @return Set containing the collection resulted
     */
    @Override
    public Iterable<Director> getDirectorsWithAgeSmallerThen(int margin) {
        return this.directorRepo.findAll()
                .stream()
                .filter(m -> m.getAge() <= margin).collect(Collectors.toSet());
    }

    /**
     * Get percentahe of directors that are younger then a given age
     *
     * @param age given age
     * @return Double value representing the requested percentage
     */
    @Override
    public Double getPercentageOfYoungDirectors(int age) {
        double youngDirectors = this.directorRepo.findAll()
                .stream()
                .filter(director -> director.getAge() <= age)
                .count();
        double allDirectors = this.directorRepo.count();
        return (youngDirectors * 100) / allDirectors;
    }
}