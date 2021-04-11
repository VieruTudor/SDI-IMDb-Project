import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.ClientConsole;

public class ClientApp {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("client");
        ClientConsole console = context.getBean(ClientConsole.class);
        console.run();
        System.out.println("done");
    }
}
