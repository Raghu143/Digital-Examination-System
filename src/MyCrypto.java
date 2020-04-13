import org.apache.commons.net.util.Base64;
public class MyCrypto {
    public static String encrypt(String texto) {
        return new String(Base64.encodeBase64(texto.getBytes()));
    }
    public static String decrypt(String texto) {
        return new String(Base64.decodeBase64(texto.getBytes()));
    }
}