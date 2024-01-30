package com.banking;

import com.banking.dto.LichSuGiaoDichDTO;
import com.banking.entity.GioiTinh;
import com.banking.repository.IKhachHangRepository;
import com.banking.repository.IUserRepository;
import com.banking.utils.CallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.time.LocalDate;

@SpringBootApplication
public class BankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);
    }


}
