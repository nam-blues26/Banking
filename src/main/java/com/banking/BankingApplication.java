package com.banking;

import com.banking.entity.GioiTinh;
import com.banking.repository.IKhachHangRepository;
import com.banking.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class BankingApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);
    }

    @Autowired
    private IKhachHangRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteKhachhang(Long.valueOf(2));
    }
}
