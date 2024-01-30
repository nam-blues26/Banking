package com.banking.RSA;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class DecrypRSA {
    public String decryp(String chuoiMaHoa) {
        try {
            // Đọc file chứa private key
            FileInputStream fis = new FileInputStream("D:/privateKey.rsa");
            byte[] b = new byte[fis.available()];
            fis.read(b);
            fis.close();

            // Tạo private key
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = factory.generatePrivate(spec);

            // Giải mã dữ liệu
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.DECRYPT_MODE, priKey);
            byte decryptOut[] = c.doFinal(Base64.getDecoder().decode(chuoiMaHoa));
            return new String(decryptOut);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
