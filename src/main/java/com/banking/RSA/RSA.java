package com.banking.RSA;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

public class RSA {

    public void SecurityKeyPairGenerator (){
    try {
        SecureRandom sr = new SecureRandom();
        // Thuật toán phát sinh khóa - RSA
        //  độ dài khóa  là 2048 2048
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048, sr);

        // Khởi tạo cặp khóa
        KeyPair kp = kpg.genKeyPair();
        // PublicKey
        PublicKey publicKey = kp.getPublic();
        // PrivateKey
        PrivateKey privateKey = kp.getPrivate();

        File publicKeyFile = createKeyFile(new File("D:/publicKey.rsa"));
        File privateKeyFile = createKeyFile(new File("D:/privateKey.rsa"));

        // Lưu Public Key
        FileOutputStream fos = new FileOutputStream(publicKeyFile);
        fos.write(publicKey.getEncoded());
        fos.close();

        // Lưu Private Key
        fos = new FileOutputStream(privateKeyFile);
        fos.write(privateKey.getEncoded());
        fos.close();

        System.out.println("Generate key successfully");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    private static File createKeyFile(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
        return file;
    }
}
