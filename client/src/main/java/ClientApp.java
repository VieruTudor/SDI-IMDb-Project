import config.ClientConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.ClientConsole;

import java.io.IOException;

public class ClientApp {
    public static void main(String[] args){
        System.out.println("please");
        try {
            System.out.println("uta");
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
            System.out.println("muie voua ");
            ClientConsole console = context.getBean(ClientConsole.class);
            console.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }
}
