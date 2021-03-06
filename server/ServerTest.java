import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ServerTest extends Thread
{
   private ServerSocket serverSocket;
   
   public ServerTest(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(10000);
   }

   public void run()
   {
      while(true)
      {
         try
         {
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "
                  + server.getRemoteSocketAddress());
            DataInputStream in =
                  new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());
            DataOutputStream out =
                 new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to "
              + server.getLocalSocketAddress() + "\nGoodbye!");
            String cIn;
            Scanner sc = new Scanner(System.in);
            String sysIn;
            while(true){
	      sysIn = sc.nextLine();
	      out.writeUTF(sysIn);
	      cIn = in.readUTF();
	      System.out.println(cIn);
	      if(cIn.equals("exit")) break;
            }
            
            server.close();
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
   public static void main(String [] args)
   {
      int port = Integer.parseInt(args[0]);
      try
      {
         Thread t = new ServerTest(port);
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}