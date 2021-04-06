package config;

import domain.serializers.ICSVSerializer;
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
    RmiServiceExporter actorExporter(IActorService actorService) {
        Class<IActorService> serviceInterface = IActorService.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(actorService);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(1234);
        return exporter;
    }

    @Bean
    RmiServiceExporter directorExporter(IDirectorService directorService) {
        Class<IDirectorService> serviceInterface = IDirectorService.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(directorService);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(1234);
        return exporter;
    }

    @Bean
    RmiServiceExporter movieExporter(IMovieService movieService) {
        Class<IMovieService> serviceInterface = IMovieService.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(movieService);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(1234);
        return exporter;
    }

    @Bean
    RmiServiceExporter playsInExporter(IPlaysInService playsInService) {
        Class<IPlaysInService> serviceInterface = IPlaysInService.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(playsInService);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(1234);
        return exporter;
    }
}