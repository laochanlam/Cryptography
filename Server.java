import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server {
  public static int port = 3002;

  public static void main(String args[]) throws Exception {
    ServerSocket ss = new ServerSocket(port);
    while (true) { //connected
      Socket server = ss.accept();
     
      InputStream input = server.getInputStream();
      BufferedReader reader = new BufferedReader(
      	new InputStreamReader(input)
	);


      String msg = reader.readLine();
      System.out.println(msg);
      
      input.close();
      server.close();

      System.out.println("Someone Connected");
    }
  }
}
