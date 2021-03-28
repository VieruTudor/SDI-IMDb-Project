import controller.ActorController;
import controller.DirectorController;
import controller.MovieController;
import controller.PlaysInController;
import view.Console;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ActorController actorController = new ActorController(executorService);
        DirectorController directorController = new DirectorController(executorService);
        MovieController movieController = new MovieController(executorService);
        PlaysInController playsInController = new PlaysInController(executorService);

        Console console = new Console(actorController, directorController, movieController, playsInController);
        console.run();
    }
}
