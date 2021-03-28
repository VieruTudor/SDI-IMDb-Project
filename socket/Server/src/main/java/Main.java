import server.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        Server server = new Server(executorService);
        try {
            server.run();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}