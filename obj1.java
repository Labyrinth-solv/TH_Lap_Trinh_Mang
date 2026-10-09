
import java.io.*;
import java.net.*;

import TCP.Laptop;

public class obj1 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("36.50.135.242", 2209)) {
            socket.setSoTimeout(5000);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject("B23DCCN171;zmMxxaOW");
            out.flush();
            Laptop laptop = (Laptop) in.readObject();
            String name=laptop.GetName();
            int n=laptop.GetQuantity();
            String[] arr=name.split("\\s+");
            String temp=arr[0];
            arr[0]=arr[arr.length-1];
            arr[arr.length-1]=temp;
            laptop.SetName(String.join(" ",arr));
            int newN=Integer.parseInt(new StringBuilder(String.valueOf(n)).reverse().toString());
            laptop.SetQuantity((newN));
            out.writeObject(laptop);
            out.flush();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
