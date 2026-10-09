import java.net.*;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.io.*;

public class zip1 {
    public static void main(String[] args) {
            try (Socket socket = new Socket("36.50.135.242", 2210);){
            GZIPOutputStream gzipOut = new GZIPOutputStream(socket.getOutputStream());
            PrintWriter out=new PrintWriter(new OutputStreamWriter(gzipOut));
            out.print("B23DCCN171;vVBOlusG\n");
            out.flush();
            gzipOut.finish();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(new GZIPInputStream(socket.getInputStream()), "UTF-8"));
            String s = in.readLine();
            String rev = new StringBuilder(s).reverse().toString();
            String base64 = Base64.getEncoder().encodeToString(rev.getBytes("UTF-8"));
            String res=rev + "|" + base64 + "\n";
            out.print(res);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
