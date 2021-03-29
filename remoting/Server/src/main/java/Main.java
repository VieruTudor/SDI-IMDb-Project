import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import server.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext("java.config.Config");
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