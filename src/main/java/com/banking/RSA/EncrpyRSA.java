package com.banking.RSA;

import com.banking.entity.LichSuGiaoDich;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class EncrpyRSA {
    public String encrpy(String chuoi) {
        try {
            // Đọc file chứa public key
            FileInputStream fis = new FileInputStream("D:/publicKey.rsa");
            byte[] b = new byte[fis.available()];
            fis.read(b);
            fis.close();

            // Tạo public key
            X509EncodedKeySpec spec = new X509EncodedKeySpec(b);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = factory.generatePublic(spec);

            // Mã hoá dữ liệu
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.ENCRYPT_MODE, pubKey);
            byte encryptOut[] = c.doFinal(chuoi.getBytes());
            String strEncrypt = Base64.getEncoder().encodeToString(encryptOut);
            return strEncrypt;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "that bai";
    }

}
