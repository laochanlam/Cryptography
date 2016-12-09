import java.net.ServerSocket;
import java.net.Socket;
import javax.crypto.*;
import java.security.*;
import sun.misc.*;
import javax.crypto.spec.*;
import java.io.*;
import java.util.Scanner;

public class Client {


public static int port = 3002;
public static void main(String args[]) throws Exception {


        Scanner scanner = new Scanner(System.in);
        System.out.println("ECB:1 CBC:2 CFB:3 OFB:4 CTR:5");

        Socket client = new Socket("127.0.0.1", port);
        DataOutputStream out = new DataOutputStream(client.getOutputStream());
        int option = scanner.nextInt();

        Scanner anotherscanner = new Scanner(System.in);
        String plainText = anotherscanner.nextLine();

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


        out.writeInt(option);
///////////////////////////option////////////////////////////////////

        switch(option) {


        case 1: //ECB



                Cipher cipherECB = Cipher.getInstance("DES/ECB/PKCS5Padding");
                cipherECB.init(Cipher.ENCRYPT_MODE, secretkey, random);
                byte[] cipherDataECB = cipherECB.doFinal(plainText.getBytes("UTF-8"));
                //Encrypt

                System.out.println("<<<<ECB>>>>");
                System.out.println("PlainText : " + plainText);

                //print P & C & Method on screen

                OutputStream sentECB = client.getOutputStream();
                sentECB.write(cipherDataECB);
                //send data

                sentECB.close();
                client.close();
                break;

        case 2: //CFB

                Cipher cipherCFB = Cipher.getInstance("DES/CFB/PKCS5Padding");
                cipherCFB.init(Cipher.ENCRYPT_MODE, secretkey, ivspec);
                byte[] cipherDataCFB = cipherCFB.doFinal(plainText.getBytes("UTF-8"));
                //Encrypt

                System.out.println("<<<<CFB>>>>");
                System.out.println("PlainText :" + plainText);

                //print P & C & Method on screen

                OutputStream sentCFB = client.getOutputStream();
                sentCFB.write(cipherDataCFB);
                //send data

                sentCFB.close();
                client.close();
                break;

        case 3: //CBC

                Cipher cipherCBC = Cipher.getInstance("DES/CBC/PKCS5Padding");
                cipherCBC.init(Cipher.ENCRYPT_MODE, secretkey, ivspec);
                byte[] cipherDataCBC = cipherCBC.doFinal(plainText.getBytes("UTF-8"));
                //Encrypt

                System.out.println("<<<<CBC>>>>");
                System.out.println("PlainText :" + plainText);

                //print P & C & Method on screen

                OutputStream sentCBC = client.getOutputStream();
                sentCBC.write(cipherDataCBC);
                //send data

                sentCBC.close();
                client.close();
                break;

        case 4:                 //OFB

                Cipher cipherOFB = Cipher.getInstance("DES/OFB/PKCS5Padding");
                cipherOFB.init(Cipher.ENCRYPT_MODE, secretkey, ivspec);
                byte[] cipherDataOFB = cipherOFB.doFinal(plainText.getBytes("UTF-8"));
                //Encrypt

                System.out.println("<<<<OFB>>>>");
                System.out.println("PlainText :" + plainText);

                //print P & C & Method on screen

                OutputStream sentOFB = client.getOutputStream();
                sentOFB.write(cipherDataOFB);
                //send data

                sentOFB.close();
                client.close();
                break;


        case 5:         //CTR

                Cipher cipherCTR = Cipher.getInstance("DES/CTR/NoPadding");
                cipherCTR.init(Cipher.ENCRYPT_MODE, secretkey, ivspec);
                byte[] cipherDataCTR = cipherCTR.doFinal(plainText.getBytes("UTF-8"));
                //Encrypt

                System.out.println("<<<<CTR>>>>");
                System.out.println("PlainText :" + plainText);

                //print P & C & Method on screen

                OutputStream sentCTR = client.getOutputStream();
                sentCTR.write(cipherDataCTR);
                //send data

                sentCTR.close();
                client.close();
                break;


        }


}
}
