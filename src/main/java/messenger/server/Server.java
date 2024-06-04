package messenger.server;

import messenger.entities.Message;
import messenger.entities.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Server {
    // Initiate pseudo DB for active users and messages
    private static List<Message> messages = new ArrayList<>();
    private static Set<User> users = new HashSet<>();
    private static boolean exit = true;
    private static int serverPort = 10001;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Initiate pseudo DB for active users and messages
        System.out.println("Server started on port: " + serverPort);
        // create server connection
        ServerSocket serverSocket = new ServerSocket(serverPort);
        // Instantiate main hearing cycle
        while(exit) {
            Socket clientSocket = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            messages.add((Message)ois.readObject());

            users.forEach(System.out::println);
            messages.forEach(System.out::println);

        }

        serverSocket.close();
    }

    public static void determineHeader(Message message) {
        switch (message.getType().toLowerCase()) {
            case "adding":
                addUser(message.getSender());
                break;
            case "send":
                sendMessage(message);
                break;
            default:
                System.err.println("Invalid Type");
        }
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void sendMessage(Message message) {

    }
}
