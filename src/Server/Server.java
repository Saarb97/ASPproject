package Server;

import java.io.IOException;
import java.net.ServerSocket;

import Server.IServerStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private boolean stop;
    private ExecutorService threadPool;


    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        Configurations config = Configurations.getInstance();
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.stop = false;
        int numThreads = config.getNumThreads();
        threadPool = Executors.newFixedThreadPool(numThreads);
    }

    public void start() {
        new Thread(this::run).start();

    }

    private void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
//            LOG.info("Starting server at port = " + port);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();

//                    LOG.info("Client accepted: " + clientSocket.toString());

                    // This thread will handle the new Client
                    threadPool.execute(() -> handleClient(clientSocket));

                } catch (SocketTimeoutException e) {
//                    LOG.debug("Socket timeout");
                }
            }
            threadPool.shutdown();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
//            LOG.info("Done handling client: " + clientSocket.toString());
            clientSocket.close();
        } catch (IOException e) {
//            LOG.error("IOException", e);
        }
    }

    public void stop() {
//        LOG.info("Stopping server...");
        stop = true;
    }
}