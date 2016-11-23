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

    String plainText = "Hello ,This is CFB !";
    String key = "12345678";
    byte [] iv = {1,1,1,1,0,0,0,0};
    IvParameterSpec ivspec = new IvParameterSpec(iv);
	//Key , PlainText & IV

    SecureRandom random = new SecureRandom(); 
	//random num generation

    DESKeySpec keyspec = new DESKeySpec(key.getBytes());
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey secretkey = keyFactory.generateSecret(keyspec);
	//key generation

    Cipher cipher = Cipher.getInstance("DES/CFB/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, secretkey , ivspec); 
    byte[] cipherData = cipher.doFinal(plainText.getBytes("UTF-8"));
	//Encrypt

    Socket client = new Socket("127.0.0.1", port);
    	//connect server

    System.out.println("<<<<CFB>>>>");
    System.out.println("PlainText :" + plainText);
    System.out.println("CipherText:" + cipherData);
	//print P & C & Method on screen 

    OutputStream sent = client.getOutputStream();
    sent.write(cipherData);
	//send data

    sent.close();
    client.close();

  }
}

