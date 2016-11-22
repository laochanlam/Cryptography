import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import javax.crypto.*;
import java.security.*;
import sun.misc.*;
import javax.crypto.spec.*;

public class Server {
  public static int port = 3002;

  public static void main(String args[]) throws Exception {
    ServerSocket ss = new ServerSocket(port);
    byte[] iv = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
    IvParameterSpec ivspec = new IvParameterSpec(iv);

    while (true) { //connected
      Socket server = ss.accept();
     
      InputStream input = server.getInputStream();
      BufferedReader reader = new BufferedReader(
      	new InputStreamReader(input)
	);

      String msg = reader.readLine();
	



      String key = "12345678";

      SecureRandom random = new SecureRandom();  //just random

      DESKeySpec keyspec = new DESKeySpec(key.getBytes());
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey secretkey = keyFactory.generateSecret(keyspec);
	//key generation

   
      Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE , secretkey);
      byte[] plainData = cipher.doFinal(msg.getBytes());
     System.out.println(msg);
      
      input.close();
      server.close();



      System.out.println("Someone Connected");
    }
  }
}
