import java.net.*;
import java.io.*;;

public class data2 {
    public static void main(String[] args) {
        try(Socket socket=new Socket("36.50.135.242", 2207)){
            socket.setSoTimeout(5000);
            DataInputStream in=new DataInputStream(socket.getInputStream());
            DataOutputStream out=new DataOutputStream(socket.getOutputStream());
            out.writeUTF("B23DCCN171;tiBGrNao");
            out.flush();
            String str=in.readUTF();
            int c=in.readInt();
            StringBuilder ss=new StringBuilder();
            for(int i=0; i<str.length(); i++){
                if(str.charAt(i)>='A' && str.charAt(i)<='Z')
                    ss.append((char)('A'+(str.charAt(i)-'A'-c+26)%26));
                else if(str.charAt(i)>='a' && str.charAt(i)<='z'){
                    ss.append((char)('a'+(str.charAt(i)-'a'-c+26)%26));
                }
                else{
                    ss.append(str.charAt(i));
                }
            }
            out.writeUTF(ss.toString());
            out.flush();
        }   
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}
