package config;

import controllers.*;
import interfaces.*;
import networking.ServerInformation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import view.ResponseBuffer;
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
    ResponseBuffer responseBuffer(){
        return new ResponseBuffer();
    }

    @Bean
    RmiProxyFactoryBean rmiActorProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IActorController.class);
        String url = String.format("rmi://localhost:%d/ActorController", ServerInformation.PORT);
        rmiProxyFactoryBean.setServiceUrl(url);
        return rmiProxyFactoryBean;
    }

    @Bean
    RmiProxyFactoryBean rmiMovieProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IMovieController.class);
        String url = String.format("rmi://localhost:%d/MovieController", ServerInformation.PORT);
        rmiProxyFactoryBean.setServiceUrl(url);
        return rmiProxyFactoryBean;
    }

    @Bean
    RmiProxyFactoryBean rmiDirectorProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IDirectorController.class);
        String url = String.format("rmi://localhost:%d/DirectorController", ServerInformation.PORT);
        rmiProxyFactoryBean.setServiceUrl(url);
        return rmiProxyFactoryBean;
    }

    @Bean
    RmiProxyFactoryBean rmiPlaysInProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(IPlaysInController.class);
        String url = String.format("rmi://localhost:%d/PlaysInController", ServerInformation.PORT);
        rmiProxyFactoryBean.setServiceUrl(url);
        return rmiProxyFactoryBean;
    }
}
