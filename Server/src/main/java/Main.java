import config.AppLocalConfig;
import config.Config;
import config.JPAConfig;
import interfaces.IActorService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(AppLocalConfig.class, Config.class, JPAConfig.class);
        System.out.println("Server started");
    }
}