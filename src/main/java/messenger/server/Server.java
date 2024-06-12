package messenger.server;

import messenger.entities.Message;
import messenger.entities.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

public class Server {
    private static Logger logger = Logger.getLogger(Server.class.getName());
    private static boolean exit = false;
    private static Set<User> users = new HashSet<>();

    public void startServer() throws IOException {

        Thread serverThread = new Thread(new ServerListener());
        serverThread.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 'exit' to stop the server.");
        String command;
        while (scanner.hasNextLine()) {
            command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                exit = true;
                try {
                    serverThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Server Stopped");
                break;
            }
        }



    }

    // Create new thread for each socket
    static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try (ObjectInputStream ois =
                         new ObjectInputStream(clientSocket.getInputStream())) {
                Message message = (Message) ois.readObject();
                users.add(new User(message.getSender().getName(), clientSocket));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Continuously listen for new connection
    static class ServerListener implements Runnable {
        @Override
        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(10001)) {
                logger.info("Server is running on port 10000");
                while (!exit) {
                    Socket clientSocket = serverSocket.accept();
                    logger.info("New Client Add");
                    new ClientHandler(clientSocket).start();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

