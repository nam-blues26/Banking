package com.banking.constant;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@RequiredArgsConstructor
public class MessageConstant {

    // Not found exception
    public static final String KH_NOT_FOUND = "khachhang.notfound";

    //Không nhập
    public static final String KH_SDT_NOT_BLANK = "khachhang.sdt.notblank";
    public static final String KH_CCCD_NOT_BLANK = "khachhang.cccd.notblank";
    public static final String KH_HOTEN_NOT_BLANK = "khachhang.hoten.notblank";
    public static final String KH_NGAYSINH_NOT_NULL = "khachhang.ngaysinh.notnull";

    //Nhập quá ký tự
    public static final String KH_SDT_MAX_LENGHT = "khachhang.sdt.maxlength";
    public static final String KH_CCCD_MAX_LENGHT = "khachhang.cccd.maxlength";

    // Regex chỉ nhập số
    public static final String KH_SDT_REGEX = "khachhang.sdt.regex";
    public static final String KH_CCCD_REGEX = "khachhang.cccd.regex";
    public static final String TKNH_SOTK_REGEX = "taikhoan.sotk.regex";


    //Ngày sinh là thời gian ở quá khứ
    public static final String KH_NGAYSINH_PAST = "khachhang.ngaysinh.past";

    // exist exception
    public static final String KH_CCCD_EXIST = "khachhang.cccd.exists";

    public static final String USER_EXIST = "user.username.exist";
    public static final String USER_NOT_FOUND = "user.username.notfound";

    //Sai mật khẩu hoăc tên đăng nhập
    public static final String LOGIN_FAILL ="user.login.fail";

    public static final String DELETE_SUSSCES = "delete sussces";

    public static final String USER_DOB_INVALID = "user.dob.invalid";

    public static final String USER_UNAUTHORIZED = "user.unathorized";
}
