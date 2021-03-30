package config;

import database.adapters.ActorTableAdapter;
import database.adapters.DirectorTableAdapter;
import database.adapters.MovieTableAdapter;
import database.adapters.PlaysInTableAdapter;
import interfaces.IActorController;
import interfaces.IDirectorController;
import interfaces.IMovieController;
import interfaces.IPlaysInController;
import controllers.ActorController;
import controllers.DirectorController;
import controllers.MovieController;
import controllers.PlaysInController;
import database.serializers.ActorDBTranslator;
import database.serializers.DirectorDBTranslator;
import database.serializers.MovieDBTranslator;
import database.serializers.PlayInDBTranslator;
import domain.*;
import networking.ServerInformation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import repository.DBRepository;
import repository.IRepository;

@ComponentScan
@Configuration
public class Config {


    //table adapters
    @Bean
    ActorTableAdapter actorTableAdapter(){
        return new ActorTableAdapter();
    }
    @Bean
    DirectorTableAdapter directorTableAdapter(){
        return new DirectorTableAdapter();
    }
    @Bean
    MovieTableAdapter movieTableAdapter(){
        return new MovieTableAdapter();
    }
    @Bean
    PlaysInTableAdapter playsInTableAdapter(){
        return new PlaysInTableAdapter();
    }
    //repositories
    @Bean
    IRepository<Integer, Actor> actorRepository(ActorTableAdapter actorTableAdapter){
        return new DBRepository<Integer, Actor>(actorTableAdapter);
    }
    @Bean
    IRepository<Integer, Director> directorRepository(DirectorTableAdapter directorTableAdapter){
        return new DBRepository<>(directorTableAdapter);
    }
    @Bean
    IRepository<Integer, Movie> movieRepository(MovieTableAdapter movieTableAdapter){
        return new DBRepository<>(movieTableAdapter);
    }
    @Bean
    IRepository<Pair<Integer, Integer>, PlaysIn> playsInRepository(PlaysInTableAdapter playsInTableAdapter){
        return new DBRepository<>(playsInTableAdapter);
    }

    //controllers
    @Bean
    ActorController actorController(IRepository<Integer, Actor> actorRepository){
        return new ActorController(actorRepository);
    }
    @Bean
    DirectorController directorController(IRepository<Integer, Director> directorRepository){
        return new DirectorController(directorRepository);
    }
    @Bean
    MovieController movieController(IRepository<Integer, Movie> movieRepository){
        return new MovieController(movieRepository);
    }
    @Bean
    PlaysInController playsInController(IRepository<Pair<Integer, Integer>, PlaysIn> playsInRepository){
        return new PlaysInController(playsInRepository);
    }

    //exporters
    @Bean
    RmiServiceExporter actorExporter(ActorController actorController){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("ActorController");
        rmiServiceExporter.setServiceInterface(IActorController.class);
        rmiServiceExporter.setService(actorController);
        rmiServiceExporter.setRegistryPort(ServerInformation.PORT);
        return rmiServiceExporter;
    }
    @Bean
    RmiServiceExporter directorExporter(DirectorController directorController){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("DirectorController");
        rmiServiceExporter.setServiceInterface(IDirectorController.class);
        rmiServiceExporter.setService(directorController);
        rmiServiceExporter.setRegistryPort(ServerInformation.PORT);
        return rmiServiceExporter;
    }
    @Bean
    RmiServiceExporter movieExporter(MovieController movieController){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("MovieController");
        rmiServiceExporter.setServiceInterface(IMovieController.class);
        rmiServiceExporter.setService(movieController);
        rmiServiceExporter.setRegistryPort(ServerInformation.PORT);
        return rmiServiceExporter;
    }
    @Bean
    RmiServiceExporter playsInExporter(PlaysInController playsInController){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("PlaysInController");
        rmiServiceExporter.setServiceInterface(IPlaysInController.class);
        rmiServiceExporter.setService(playsInController);
        rmiServiceExporter.setRegistryPort(ServerInformation.PORT);
        return rmiServiceExporter;
    }

}
