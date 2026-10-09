import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class bytes2 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("36.50.135.242", 2206)) {
            InputStream in=socket.getInputStream();
            OutputStream out=socket.getOutputStream();
            String f="B23DCCN171;UHM9Jmru";
            out.write(f.getBytes());
            out.flush();
            byte[] buffer=new byte[1024];
            int len=in.read(buffer);
            String s=new String(buffer, 0, len);
            String[] num=s.split(",");
            ArrayList<Integer> res=new ArrayList<>();
            
            for(int i=0; i<num.length; i++){
                res.add(Integer.parseInt(num[i]));
            }
            int max=res.get(0);
            for(int i=1; i<res.size(); i++){
                if(max<res.get(i)){
                    max=res.get(i);
                }
            }
            int second=res.get(0);
            int index=0;
            for(int i=0; i<res.size(); i++){
                if(second==max){
                    second=res.get(i+1);
                    continue;
                }
                if(second<res.get(i) && res.get(i)<max){
                    second=res.get(i);
                    index=i;
                }
            }
            String a=second+","+index;
            out.write(a.getBytes());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
