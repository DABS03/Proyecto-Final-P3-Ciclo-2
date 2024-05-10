package blockchain;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cifrado implements Serializable
{
    private SecretKeySpec llave;
    private Cipher oCifrado;    // Encriptador
    private Cipher oDescifrado; // Desencriptador

    public Cifrado(String pClave) 
    {
        try 
        {
            MessageDigest oHash = MessageDigest.getInstance("SHA-256"); //SHA-256 en vez de SHA-1
            byte[] aBytes = oHash.digest(pClave.getBytes("UTF-8"));
            byte[] aBytes24 = Arrays.copyOf(aBytes, 24); // 4 bytes para Triple DES
            this.llave = new SecretKeySpec(aBytes24, "DESede"); // "DESede" EN VEZ DE AES

            this.oCifrado = Cipher.getInstance("DESede/ECB/PKCS5Padding"); //
            this.oCifrado.init(Cipher.ENCRYPT_MODE, this.llave);

            this.oDescifrado = Cipher.getInstance("DESede/ECB/PKCS5Padding");//
            this.oDescifrado.init(Cipher.DECRYPT_MODE, this.llave);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public String encriptar(String pCadena) throws Exception 
    {
        byte[] aBytes = pCadena.getBytes("UTF-8");
        byte[] aBytesEnc = this.oCifrado.doFinal(aBytes);
        return Base64.getEncoder().encodeToString(aBytesEnc);
    }

    public String desencriptar(String pCadena) throws Exception 
    {
        byte[] aBytes = Base64.getDecoder().decode(pCadena);
        byte[] aBytesDec = this.oDescifrado.doFinal(aBytes);
        String datos = new String(aBytesDec, "UTF-8");
        return datos;
    }
}