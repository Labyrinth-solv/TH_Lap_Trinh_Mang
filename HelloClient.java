
import java.io.*;
import java.net.*;

public class HelloClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("127.0.0.1", 9091);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {

            out.println("Xin chao tu Client!");
            System.out.println("Phan hoi tu server: " + in.readLine());
        }
    }
}