package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import services.ActorService;
import services.DirectorService;
import services.MovieService;
import services.PlaysInService;
import interfaces.IActorService;
import interfaces.IDirectorService;
import interfaces.IMovieService;
import interfaces.IPlaysInService;

@Configuration
@ComponentScan({"repository", "services", "interfaces"})
public class Config {
    @Bean
    RmiServiceExporter actorExporter(ActorService actorController){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("ActorService");
        rmiServiceExporter.setServiceInterface(IActorService.class);
        rmiServiceExporter.setService(actorController);
        rmiServiceExporter.setRegistryPort(1234);
        return rmiServiceExporter;
    }
    @Bean
    RmiServiceExporter directorExporter(DirectorService directorService){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("DirectorService");
        rmiServiceExporter.setServiceInterface(IDirectorService.class);
        rmiServiceExporter.setService(directorService);
        rmiServiceExporter.setRegistryPort(1234);
        return rmiServiceExporter;
    }
    @Bean
    RmiServiceExporter movieExporter(MovieService movieService){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("MovieService");
        rmiServiceExporter.setServiceInterface(IMovieService.class);
        rmiServiceExporter.setService(movieService);
        rmiServiceExporter.setRegistryPort(1234);
        return rmiServiceExporter;
    }
    @Bean
    RmiServiceExporter playsInExporter(PlaysInService playsInService){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("PlaysInService");
        rmiServiceExporter.setServiceInterface(IPlaysInService.class);
        rmiServiceExporter.setService(playsInService);
        rmiServiceExporter.setRegistryPort(1234);
        return rmiServiceExporter;
    }
}