import controller.ActorController;
import controller.DirectorController;
import controller.MovieController;
import controller.PlaysInController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.Console;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        new AnnotationConfigApplicationContext("src.main.java.config.Config");
    }
}
