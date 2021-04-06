import config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext("config");
        System.out.println("Server started");
    }
}