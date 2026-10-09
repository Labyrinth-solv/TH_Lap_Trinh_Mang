import java.io.*;
import java.net.*;
import java.util.*;

public class bytestream {
    public static void main(String[] args) {
        try (Socket socket = new Socket("36.50.135.242", 2206)) {
            socket.setSoTimeout(5000);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            out.write("B23DCCN171;5bNsJvuX".getBytes());
            out.flush();

            byte[] buffer = new byte[1024];
            int len = in.read(buffer);
            String response = new String(buffer, 0, len);

            String[] s = response.trim().split(",");
            ArrayList<Integer> numbers = new ArrayList<>();
            for (String x : s) numbers.add(Integer.parseInt(x.trim()));

            numbers.sort(null);

            int min = numbers.get(1) - numbers.get(0);
            int first = numbers.get(0);
            int second = numbers.get(1);

            for (int i = 1; i < numbers.size() - 1; i++) {
                int distance = numbers.get(i + 1) - numbers.get(i);
                if (distance < min) {
                    min = distance;
                    first = numbers.get(i);
                    second = numbers.get(i + 1);
                }
            }

            String result = min + "," + first + "," + second;
            out.write(result.getBytes());
            out.flush();

            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}