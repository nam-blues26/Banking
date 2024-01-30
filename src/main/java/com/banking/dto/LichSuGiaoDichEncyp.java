package com.banking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LichSuGiaoDichEncyp {
    private String accDi;
    private String accNhan;
    private String intDebt;
    private String have;
    private String time;
    private String tran;
}
