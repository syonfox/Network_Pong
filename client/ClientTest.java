import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientTest {

  public static void main(String[] args) {
    String serverName = args[0];
    int port = Integer.parseInt(args[1]);
    Scanner sc;
    try
      {
         System.out.println("Connecting to " + serverName + " on port " + port);
         Socket client = new Socket(serverName, port);
         System.out.println("Just connected to " + client.getRemoteSocketAddress());
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         out.writeUTF("Hello from "
                      + client.getLocalSocketAddress());
                   
         InputStream inFromServer = client.getInputStream();
         DataInputStream in =
                        new DataInputStream(inFromServer);
         System.out.println("Server says " + in.readUTF());
         
         boolean running = true;
         sc = new Scanner(System.in);
         String sysIn;
         String sIn;
         while(running) {
	    sysIn = sc.nextLine();
	    if(sysIn.equals("exit")) running=false;
	    sIn = in.readUTF();
	    System.out.println(sIn);
	    out.writeUTF(sysIn);
         }
         
         client.close();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
      
  }
  
}