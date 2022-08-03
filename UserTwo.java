import java.util.*;
import java.net.*;
import java.io.*;
public class UserTwo implements Runnable {
    
    BufferedReader reader;
    BufferedWriter writer;

    public UserTwo()
    {
        try {
            Socket socket=new Socket("localhost",2003);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));     
            
        } catch (Exception e) {
            //TODO: handle exception
        }
           

           System.out.println("enter message");
           Scanner ss=new Scanner(System.in);
           String data=ss.next();
           try {
               writer.write(data);
               writer.write("\n");
               writer.flush();
           } catch (Exception e) {
               //TODO: handle exception
           }
    }

    @Override
    public void run()
    {
             String msg="";
             try {
                while((msg=reader.readLine().trim())!=null)
                {
                    System.out.println("Recived essage:- "+msg);
                }
             } catch (Exception e) {
                 //TODO: handle exception
             }
             

    }

    public static void main(String[] args)
    {
        UserTwo two=new UserTwo();
        Thread t1=new Thread(two);
        t1.start();
    }


}
