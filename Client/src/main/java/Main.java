import config.Config;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.Console;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class Main {
    public static void main(String[] args) throws IOException {

        var context = new AnnotationConfigApplicationContext(Config.class);
        var console = context.getBean(Console.class);
        console.run();
        var executor = context.getBean(ExecutorService.class);
        executor.shutdown();

    }
}
