package service;

import model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.DirectorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class DirectorService implements IDirectorService{
    @Autowired
    private DirectorRepository repo;

    public static final Logger log = LoggerFactory.getLogger(DirectorService.class);

    @Override
    public Director addDirector(Director director) {
        log.trace("Add director started...");
        this.repo.save(director);
        log.trace("Add director done.");
        return director;
    }

    @Override
    public void deleteDirector(int id) {
        log.trace("Delete director started...");
        this.repo.deleteById(id);
        log.trace("Delete director done");

    }
    @Transactional
    @Override
    public Director updateDirector(Director director) {
        log.trace("Update director started...");
        Director updatedDirector=this.repo.findById(director.getId()).orElseThrow();
        updatedDirector.setName(director.getName());
        updatedDirector.setAge(director.getAge());
        log.trace("Update director done");
        return director;


    }

    @Override
    public Director getById(int id) {

        return this.repo.getOne(id);
    }

    @Override
    public Iterable<Director> getAllDirectors() {
        return this.repo.findAllByOrderByIdAsc();
    }

    @Override
    public Iterable<Director> getDirectorsAgeLowerThan(int margin) {
        return this.repo.getDirectorsByAgeLessThan(margin);
    }

    @Override
    public Double getPercentageOfYoungDirectors(int age)
    {
        int directorCount=(int)this.repo.count();
        int youngDirectorCount=this.repo.countDirectorByAgeLessThanEqual(age);
        return (double) ((youngDirectorCount * 100) / directorCount);
    }
}
