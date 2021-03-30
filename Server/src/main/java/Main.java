import config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import server.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);
        System.out.println("Server started");
//        ExecutorService executorService = Executors.newFixedThreadPool(
//                Runtime.getRuntime().availableProcessors()
//        );
//        Server server = new Server(executorService);
//        try {
//            server.run();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
    }
}