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
    byte[] iv = { 1,1,0,0,1,1,0,0 };
    IvParameterSpec ivspec = new IvParameterSpec(iv);

    while (true) { //if connected
      Socket server = ss.accept();

      System.out.println("Someone Connected");

/////////////////////////CTR/////////////////////////

      InputStream input = server.getInputStream();
      BufferedReader reader = new BufferedReader(
      	new InputStreamReader(input)
      );
      int value = 0;
      byte[] buffer = new byte[20];
      while((value=input.read(buffer)) != -1);
	//Receive & format data	

      String key = "12345678";
      DESKeySpec keyspec = new DESKeySpec(key.getBytes("UTF-8"));
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey secretkey = keyFactory.generateSecret(keyspec);
	//key generation

   
      Cipher cipher = Cipher.getInstance("DES/CTR/NoPadding");
      cipher.init(Cipher.DECRYPT_MODE , secretkey , ivspec);
      byte[] plainData = cipher.doFinal(buffer);
	//Decrypt

      System.out.println(new String(plainData));
	//print out P 

      input.close();

/////////////////////////CTR//////////////////////////




      server.close();

    }
  }
}
