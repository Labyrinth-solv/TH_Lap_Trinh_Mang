import java.net.*;
import java.io.*;

public class data1 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("36.50.135.242", 2207)) {
            DataInputStream in=new DataInputStream(socket.getInputStream());
            DataOutputStream out=new DataOutputStream(socket.getOutputStream());
            out.writeUTF("B23DCCN171;9yW3HGeK");
            out.flush();
            int a=in.readInt();
            int b=in.readInt();
            out.writeInt(a+b);
            out.writeInt(a*b);
            out.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
