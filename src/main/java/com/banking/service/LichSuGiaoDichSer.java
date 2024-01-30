package com.banking.service;

import com.banking.AES.EncrypAES;
import com.banking.BankingApplication;
import com.banking.RSA.DecrypRSA;
import com.banking.RSA.EncrpyRSA;
import com.banking.RSA.RSA;
import com.banking.dto.LichSuGiaoDichDTO;
import com.banking.dto.LichSuGiaoDichEncyp;
import com.banking.entity.LichSuGiaoDich;
import com.banking.exception.LichSuNotNullException;
import com.banking.repository.ILichSuGiaoDich;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

@Service
public class LichSuGiaoDichSer {
    @Autowired
    private ILichSuGiaoDich lichSuGiaoDichRes;
    private static final Logger logger = LoggerFactory.getLogger(LichSuGiaoDichSer.class);

    private final String key = "key";

    @Transactional
    public void add(LichSuGiaoDichDTO lichSuGiaoDich) {
        System.out.println("add" + lichSuGiaoDich);
        if (lichSuGiaoDich.getAccDi().trim().length() == 0
                || lichSuGiaoDich.getAccNhan().trim().length() == 0) {
            logger.error("Error processing transaction. TransactionID: {}, Account: {}, InDebt: {}, Have: {}, Time: {}",
                    redactSensitiveInfo(lichSuGiaoDich.getTran()),
                    redactSensitiveInfo(lichSuGiaoDich.getAccDi()),
                    redactSensitiveInfo(lichSuGiaoDich.getIntDebt() + ""),
                    redactSensitiveInfo(lichSuGiaoDich.getHave() + ""));
            throw new LichSuNotNullException("Not null");
        }

        String tran = new Random().nextInt(1000000) + "";
        EncrypAES aes = new EncrypAES();
        lichSuGiaoDichRes.save(LichSuGiaoDich.builder()
                .account(aes.encrypt(lichSuGiaoDich.getAccDi(), key))
                .intDebt(lichSuGiaoDich.getIntDebt())
                .have(Double.valueOf(0))
                .transactionID(tran)
                .time(LocalDate.now())
                .build());

        lichSuGiaoDichRes.save(LichSuGiaoDich.builder()
                .account(aes.encrypt(lichSuGiaoDich.getAccNhan(), key))
                .intDebt(Double.valueOf(0))
                .have(lichSuGiaoDich.getHave())
                .transactionID(tran)
                .time(LocalDate.now())
                .build());
    }

    public LichSuGiaoDichEncyp EncypParameter(LichSuGiaoDichDTO lichSuGiaoDich) {
        EncrpyRSA rsa = new EncrpyRSA();
        return LichSuGiaoDichEncyp.builder()
                .accDi(rsa.encrpy(lichSuGiaoDich.getAccDi()))
                .accNhan(rsa.encrpy(lichSuGiaoDich.getAccNhan()))
                .have(rsa.encrpy(lichSuGiaoDich.getHave() + ""))
                .intDebt(rsa.encrpy(lichSuGiaoDich.getIntDebt() + ""))
                .tran(rsa.encrpy(lichSuGiaoDich.getTran()))
                .time(rsa.encrpy(lichSuGiaoDich.getTime() + ""))
                .build();
    }

    public LichSuGiaoDichDTO DEcypParameter(LichSuGiaoDichEncyp lichSuGiaoDich) {
        System.out.println(lichSuGiaoDich);
        DecrypRSA rsa = new DecrypRSA();
        return LichSuGiaoDichDTO.builder()
                .accDi(rsa.decryp(lichSuGiaoDich.getAccDi()))
                .accNhan(rsa.decryp(lichSuGiaoDich.getAccNhan()))
                .have(Double.parseDouble(rsa.decryp(lichSuGiaoDich.getHave())))
                .intDebt(Double.parseDouble(rsa.decryp(lichSuGiaoDich.getIntDebt())))
                .tran(rsa.decryp(lichSuGiaoDich.getTran()))
                .build();
    }

    private static String redactSensitiveInfo(String sensitiveInfo) {
        // Logic che giấu thông tin nhạy cảm ở đây, có thể thay thế bằng dấu '?'
        return "?";
    }

}
