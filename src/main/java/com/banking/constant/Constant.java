package com.banking.constant;

public class Constant {

    // Các message trả về
    public static class MessageResponse{
        // Not found exception
        public static final String KH_NOT_FOUND ="khachhang.notfound";

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
    }


    //Example value SWAGGER
    public static class SwaggerExValue{

        //Example Value Khách Hàng trong Swagger
        public static final String KH_SDT = "0112233445";
        public static final String KH_CCCD = "0123456789";
        public static final String KH_HO_TEN = "Nguyễn Văn A";
        public static final String KH_GIOI_TINH = "Nam";
        public static final String KH_NGAY_SINH = "1992-06-26";

    }
}
