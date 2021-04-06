package config;

import controllers.*;
import interfaces.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import view.Console;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class Config {

    @Bean
    Console console() {
        return new Console();
    }

    /*
    TODO: Depending on how we refactor the client to accept multiple commands (see example with ThenBy already implemented),
          this might be deprecated
     */
    @Bean
    ExecutorService executorService() {
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Bean
    FutureActorController actorController() {
        return new ActorController();
    }

    @Bean
    FutureMovieController movieController() {
        return new MovieController();
    }

    @Bean
    FutureDirectorController directorController(){
        return new DirectorController();
    }

    @Bean
    FuturePlaysInController playsInController(){
        return new PlaysInController();
    }



    @Bean
    RmiProxyFactoryBean rmiActorProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IActorService.class);
        String url = String.format("rmi://localhost:%d/ActorService", 1234);
        rmiProxyFactoryBean.setServiceUrl(url);
        return rmiProxyFactoryBean;
    }

    @Bean
    RmiProxyFactoryBean rmiMovieProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IMovieService.class);
        String url = String.format("rmi://localhost:%d/MovieService", 1234);
        rmiProxyFactoryBean.setServiceUrl(url);
        return rmiProxyFactoryBean;
    }

    @Bean
    RmiProxyFactoryBean rmiDirectorProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IDirectorService.class);
        String url = String.format("rmi://localhost:%d/DirectorService", 1234);
        rmiProxyFactoryBean.setServiceUrl(url);
        return rmiProxyFactoryBean;
    }

    @Bean
    RmiProxyFactoryBean rmiPlaysInProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IPlaysInService.class);
        String url = String.format("rmi://localhost:%d/PlaysInService", 1234);
        rmiProxyFactoryBean.setServiceUrl(url);
        return rmiProxyFactoryBean;
    }
}