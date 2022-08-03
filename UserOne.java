import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UserOne implements Runnable{
   
    
    BufferedWriter writer;
    BufferedReader reader;

    UserOne(){
      
       
       try{
           
           Socket socketClient = new Socket("localhost", 2003);
           writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
           reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
       }catch(Exception e){}

       System.out.println("enter message");

       Scanner ss=new Scanner(System.in);
       String data=ss.next();
       try{
        writer.write(data);
        writer.write("\r\n");
        writer.flush();
    }catch(Exception e){}
       
        
    }
    
    
    
    public void run(){
        try{
            String msg = "";
            while((msg = reader.readLine()) != null){
                System.out.println("Recived essage:- "+msg);
            }
        }catch(Exception e){}
    }
    
    public static void main(String[] args){
        UserOne one = new UserOne();
        Thread t1 = new Thread(one);
        t1.start();
    }
    
}