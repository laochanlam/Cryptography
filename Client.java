import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {
  public static int port = 3002;

  public static void main(String args[]) throws Exception {
    Socket client = new Socket("127.0.0.1", port);
    InputStream in = client.getInputStream();
    BufferedReader reader = new BufferedReader(
      new InputStreamReader(in)
    );

    String msg = reader.readLine();
    System.out.println(msg);
    client.close();
  }
}

