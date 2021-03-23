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

    public HandleRequest(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        try (InputStream inputStream = client.getInputStream();
             OutputStream outputStream = client.getOutputStream()) {

            var message = Message.read(inputStream);
            var response = server.HandleTask.handleTask(message);
            Objects.requireNonNull(response, "Error computing the response");
            Message.write(response, outputStream);
            client.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}