import java.io.*;
import java.net.*;
import java.util.LinkedHashMap;
import java.util.Map;;

public class character2 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("36.50.135.242", 2208)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("B23DCCN171;RLZ9cjpT");
            String s = in.readLine();
            LinkedHashMap<Character, Integer> res = new LinkedHashMap<>();
            for (int i = 0; i < s.length(); i++) {
                if (Character.isLetterOrDigit(s.charAt(i))) {
                    res.put(s.charAt(i), res.getOrDefault(s.charAt(i), 0) + 1);
                }
            }
            StringBuilder r = new StringBuilder();
            for (Map.Entry<Character, Integer> n : res.entrySet()) {
                if(n.getValue()>1){
                    r.append(n.getKey()+":"+n.getValue()).append(",");
                }
            }
            out.println(r.toString());

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
