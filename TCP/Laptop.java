package TCP;

import java.io.Serializable;

public class Laptop implements Serializable {
    private static final long serialVersionUID = 20150711L;
    private int id;
    private String code;
    private String name;
    private int quantity;
    public String GetName(){
        return name;
    }
    public int GetQuantity(){
        return quantity;
    }
    public void SetName(String s){
        name=s;        
    }
    public void SetQuantity(int n){
        quantity=n;
    }
}