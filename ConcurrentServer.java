import java.io.*;
import java.net.*;

public class ConcurrentServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9091)) {
            System.out.println("Concurrent Server dang cho tai cong 9091 ...");
            while (true) {
                Socket socket = server.accept();
                new Thread(new ClientHandler(socket)).start(); // giao cho Thread rieng
            }
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            String ten = in.readLine();
            System.out.println("[" + Thread.currentThread().getName() + "] phuc vu: " + ten);
            out.println("Xin chao, " + ten + "! Ban dang duoc phuc vu boi "
                    + Thread.currentThread().getName());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
