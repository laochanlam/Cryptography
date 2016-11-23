import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.crypto.*;
import java.security.*;
import sun.misc.*;
import javax.crypto.spec.*;

public class Client {


  public static int port = 3002;
  public static void main(String args[]) throws Exception {

    String plainText = "Hello ,This is CBC !";
    String key = "12345678";

    SecureRandom random = new SecureRandom();  //just random

    DESKeySpec keyspec = new DESKeySpec(key.getBytes());
    
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey secretkey = keyFactory.generateSecret(keyspec);
	//key generation

    Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, secretkey , random);
    byte[] cipherData = cipher.doFinal(plainText.getBytes("UTF-8"));
	//Encrypt

      for(int i=0;i<cipherData.length;++i)
	System.out.println(cipherData[i]);	



	String hihi = new String(cipherData);

      System.out.println(hihi.length());	
      System.out.println(cipherData.length);	
/*
      cipher.init(Cipher.DECRYPT_MODE , secretkey , random);
      byte[] plainData = cipher.doFinal(cipherData);
      System.out.println(new String(plainData));
 */





    Socket client = new Socket("127.0.0.1", port);

    OutputStream sent = client.getOutputStream();
    sent.write(cipherData);

    sent.close();
    client.close();

  }
}

