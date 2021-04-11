import config.ClientConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.ClientConsole;

import java.io.IOException;

public class ClientApp {
    public static void main(String[] args){
        try {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
            ClientConsole console = context.getBean(ClientConsole.class);
            console.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
