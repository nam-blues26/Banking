package com.banking.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class LichSuGiaoDichDTO {
    private String accDi;
    private String accNhan;
    private Double intDebt;
    private Double have;
    private String tran;
    private Date time;
}
