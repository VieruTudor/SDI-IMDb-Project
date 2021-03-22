package server;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

import controller.IActorController;
import networking.Message;

public class HandleRequest implements Runnable {

    private final Socket client;
    private final IActorController actorController;

    public HandleRequest(Socket client, IActorController actorController) {
        this.client = client;
        this.actorController = actorController;
    }

    @Override
    public void run() {

        try (InputStream inputStream = client.getInputStream();
             OutputStream outputStream = client.getOutputStream()) {
            var message = Message.read(inputStream);
            var response = server.HandleTask.handleTask(message, actorController);
            Objects.requireNonNull(response, "Error computing the response");


            Message.write(response, outputStream);
            client.close();

        }
        catch(IOException e){
            e.printStackTrace();
        }
        // remove this somehow
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}