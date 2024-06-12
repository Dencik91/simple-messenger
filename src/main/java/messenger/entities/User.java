package messenger.entities;

import java.io.Serializable;
import java.net.Socket;
import java.util.Objects;

public class User implements Serializable {
    private String name;
    private Socket socket;

    public User () {}

    public User(String name, Socket socket) {
        this.name = name;
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "User with name=" + name;
    }
}
