package server;

import controller.IActorController;
import networking.ServerInformation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class Server {
    private final IActorController actorController;
    private Boolean running = true;
    private ExecutorService executorService;



    public Server(IActorController actorController, ExecutorService srv) {
        this.actorController = actorController;
        this.executorService = srv;
    }

    public void run(){
        try (ServerSocket serverSocket = new ServerSocket(ServerInformation.PORT))
        {
            System.out.println("server started ");
            while(running){
                Socket client = serverSocket.accept();
                executorService.submit(new server.HandleRequest(client, actorController));
            }

            executorService.shutdown();
        }
        catch (IOException e) {
            // do something
            e.printStackTrace();
        }
    }
}