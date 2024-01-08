package com.banking.constant;

public class Constant {

    // Các message trả về
    public static class MessageResponse{
        // Not found exception
        public static final String KH_NOT_FOUND ="Khách hàng không tồn tại";

        //Không nhập
        public static final String KH_SDT_NOT_BLANK = "Số điện thoại không được để trống";
        public static final String KH_CCCD_NOT_BLANK = "Căn cước công dân không được để trống";
        public static final String KH_HOTEN_NOT_BLANK = "Họ tên không được để trống";
        public static final String KH_NGAYSINH_NOT_NULL = "Ngày sinh không được để trống";

        //Nhập quá ký tự
        public static final String KH_SDT_MAX_LENGHT = "Số điện thoại không được vượt quá 11 ký tự";
        public static final String KH_CCCD_MAX_LENGHT = "Căn cước công dân không được vượt quá 12 ký tự";

        // Regex chỉ nhập số
        public static final String KH_SDT_REGEX = "Số điện thoại chỉ được chứa số";
        public static final String KH_CCCD_REGEX = "Căn cước công dân chỉ được chứa số";
        public static final String TKNH_SOTK_REGEX = "Số tài khoản chỉ nhập số";


        //Ngày sinh là thời gian ở quá khứ
        public static final String KH_NGAYSINH_PAST = "Ngày sinh không hợp lệ";

        // exist exception
        public static final String KH_CCCD_EXIST = "Căn cước công dân đã tồn tại";
    }


    //Example value SWAGGER
    public static class SwaggerExValue{

        //Example Value Khách Hàng trong Swagger
        public static final String KH_SDT = "0112233445";
        public static final String KH_CCCD = "0123456789";
        public static final String KH_HO_TEN = "Nguyễn Văn B";
        public static final String KH_GIOI_TINH = "Nam";
        public static final String KH_NGAY_SINH = "1992-06-26";

    }
}
