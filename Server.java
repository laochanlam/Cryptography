import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import javax.crypto.*;
import java.security.*;
import sun.misc.*;
import javax.crypto.spec.*;
import java.awt.geom.Dimension2D;

public class Server {
public static int port = 3002;

public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(port);


        while (true) { //if connected
                Socket server = ss.accept();

                byte[] iv = {1,1,1,1,0,0,0,0};
                IvParameterSpec ivspec = new IvParameterSpec(iv);


                String key = "12345678";
                DESKeySpec keyspec = new DESKeySpec(key.getBytes("UTF-8"));
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                SecretKey secretkey = keyFactory.generateSecret(keyspec);
                //key generation

                DataInputStream in = new DataInputStream(server.getInputStream());
                int option = in.readInt();
		
                int length = 0;
                int i = 0;

                switch(option) {
                case 1:
// /////////////////////////ECB/////////////////////////
                        InputStream inputECB = server.getInputStream();


System.out.println("<<<<ECB>>>>");

                        Cipher cipherECB = Cipher.getInstance("DES/ECB/NoPadding");
                        cipherECB.init(Cipher.DECRYPT_MODE, secretkey);


                        int valueECB = 0;
                        byte[] bufferECB = new byte[8];
                        while((valueECB=inputECB.read(bufferECB)) != -1) {
                                byte[] plainTextECB = cipherECB.doFinal(bufferECB);
                                System.out.print(new String(plainTextECB));
                        }
                        inputECB.close();
                        server.close();
                        // /////////////////////////ECB//////////////////////////
                        break;


                case 2:         //CFB
                                /////////////////////////CFB/////////////////////////
                                /////////////////////////CFB/////////////////////////
                        InputStream inputCFB = server.getInputStream();

System.out.println("<<<<CFB>>>>");


                        Cipher cipherCFB = Cipher.getInstance("DES/CFB/NoPadding");
                        cipherCFB.init(Cipher.DECRYPT_MODE, secretkey, ivspec);


                        int valueCFB = 0;
                        byte[] bufferCFB = new byte[8];
                        byte[] bufferCFB2 = new byte[256];
                        while((valueCFB=inputCFB.read(bufferCFB)) != -1) {
                                for (i=0; i<8; i++)
                                        bufferCFB2[i+length] = bufferCFB[i];
                                length = length+8;
                        }
                        byte[] realdoCFB = new byte[length];
                        for (i=0; i<length; i++)
                                realdoCFB[i] = bufferCFB2[i];



                        byte[] plainTextCFB = cipherCFB.doFinal(realdoCFB);
                        System.out.print(new String(plainTextCFB));


                        inputCFB.close();
                        server.close();

                        break;

                case 3:                 //CFB
                                        /////////////////////////CFB/////////////////////////
                                        /////////////////////////CFB/////////////////////////
                        InputStream inputCBC = server.getInputStream();

System.out.println("<<<<CBC>>>>");


                        Cipher cipherCBC = Cipher.getInstance("DES/CBC/NoPadding");
                        cipherCBC.init(Cipher.DECRYPT_MODE, secretkey, ivspec);


                        int valueCBC = 0;
                        byte[] bufferCBC = new byte[8];
                        byte[] bufferCBC2 = new byte[256];
                        while((valueCBC=inputCBC.read(bufferCBC)) != -1) {
                                for (i=0; i<8; i++)
                                        bufferCBC2[i+length] = bufferCBC[i];
                                length = length+8;
                        }
                        byte[] realdoCBC = new byte[length];
                        for (i=0; i<length; i++)
                                realdoCBC[i] = bufferCBC2[i];



                        byte[] plainTextCBC = cipherCBC.doFinal(realdoCBC);
                        System.out.print(new String(plainTextCBC));


                        inputCBC.close();
                        server.close();

                        break;

                case 4:                                 //CFB
                                                        /////////////////////////CFB/////////////////////////
                        InputStream inputOFB = server.getInputStream();

System.out.println("<<<<OFB>>>>");


                        Cipher cipherOFB = Cipher.getInstance("DES/OFB/NoPadding");
                        cipherOFB.init(Cipher.DECRYPT_MODE, secretkey, ivspec);


                        int valueOFB = 0;
                        byte[] bufferOFB = new byte[8];
                        byte[] bufferOFB2 = new byte[256];
                        while((valueOFB=inputOFB.read(bufferOFB)) != -1) {
                                for (i=0; i<8; i++)
                                        bufferOFB2[i+length] = bufferOFB[i];
                                length = length+8;
                        }
                        byte[] realdoOFB = new byte[length];
                        for (i=0; i<length; i++)
                                realdoOFB[i] = bufferOFB2[i];



                        byte[] plainTextOFB = cipherOFB.doFinal(realdoOFB);
                        System.out.print(new String(plainTextOFB));


                        inputOFB.close();
                        server.close();

                        break;

                case 5:                         //CFB
                        InputStream inputCTR = server.getInputStream();

System.out.println("<<<<CTR>>>>");


                        Cipher cipherCTR = Cipher.getInstance("DES/CTR/NoPadding");
                        cipherCTR.init(Cipher.DECRYPT_MODE, secretkey, ivspec);


                        int valueCTR = 0;
                        byte[] bufferCTR = new byte[1];
                        byte[] bufferCTR2 = new byte[256];
                        while((valueCTR=inputCTR.read(bufferCTR)) != -1) {
                                for (i=0; i<1; i++)
                                        bufferCTR2[i+length] = bufferCTR[i];
                                length = length+1;
                        }
                        byte[] realdoCTR = new byte[length];
                        for (i=0; i<length; i++)
                                realdoCTR[i] = bufferCTR2[i];



                        byte[] plainTextCTR = cipherCTR.doFinal(realdoCTR);
                        System.out.print(new String(plainTextCTR));


                        inputCTR.close();
                        server.close();

                        break;
		case 6:
			return;
                }
		System.out.println();
        }
}
}
