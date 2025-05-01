package CryptoRC4;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CryptoRc4 {
    private byte[] key = "fhsd6f86f67rt8fw78fw789we78r9789wer6re".getBytes(StandardCharsets.UTF_8);
    private byte[] nonce = "nonce".getBytes(StandardCharsets.UTF_8);
    private Cipher rc4Stream;
    private Cipher rc4Stream2;

    public CryptoRc4() {
        try {
            byte[] fullKey = concat(key, nonce);
            SecretKeySpec keySpec = new SecretKeySpec(fullKey, "RC4");

            rc4Stream = Cipher.getInstance("RC4");
            rc4Stream.init(Cipher.ENCRYPT_MODE, keySpec);
            rc4Stream.update(fullKey);

            rc4Stream2 = Cipher.getInstance("RC4");
            rc4Stream2.init(Cipher.ENCRYPT_MODE, keySpec);
            rc4Stream2.update(fullKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] decrypt(byte[] data) {
        try {
            return rc4Stream.update(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] encrypt(byte[] data) {
        try {
            return rc4Stream2.update(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] concat(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }
}
