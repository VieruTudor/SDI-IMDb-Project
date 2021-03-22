package server;

import controller.IActorController;
import networking.ServerInformation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class Server {
    private Boolean running = true;
    private ExecutorService executorService;



    public Server(ExecutorService srv) {
        this.executorService = srv;
    }

    public void run(){
        try (ServerSocket serverSocket = new ServerSocket(ServerInformation.PORT))
        {
            System.out.println("server started ");
            while(running){
                Socket client = serverSocket.accept();
                executorService.submit(new server.HandleRequest(client));
            }

            executorService.shutdown();
        }
        catch (IOException e) {
            // do something
            e.printStackTrace();
        }
    }
}