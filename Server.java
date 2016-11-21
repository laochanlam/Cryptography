import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;

public class Server {
  public static int port = 3002;

  public static void main(String args[]) throws Exception {
    ServerSocket ss = new ServerSocket(port);
    while (true) {
      Socket sc = ss.accept();
      OutputStream os = sc.getOutputStream();


      os.write("hihihihi".getBytes("UTF-8"));
      os.close();
      sc.close();
    }
  }
}
