package messenger.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import messenger.entities.Message;
import messenger.entities.User;

public class Client {
    private static int portOut = 10001;
    private static int portIn = 10002;
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",portOut);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        User user = new User("Valerii");

        Message message = new Message(new User("Jenel"),
                user, "Darowa lashara!", "adding");

        oos.writeObject(message);
        oos.close();
        socket.close();
        System.out.println("Client: sent frame");
    }
}
