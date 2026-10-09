import java.net.*;
import java.io.*;;

public class Tcp {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("36.50.135.242", 2206);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {
            socket.setSoTimeout(5000);
            out.println("B23DCCN171;5bNsJvuX");
            String respones = in.readLine();
            String[] domain = respones.split(",");
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < domain.length; i++) {
                String s = domain[i].trim();
                if (s.endsWith(".edu")) {
                    if(result.length()>0) result.append(", ");
                    result.append(s);
                }

            }
            out.println(result.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
