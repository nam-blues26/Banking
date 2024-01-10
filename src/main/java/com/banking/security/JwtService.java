package com.banking.security;

import com.banking.dto.TokenDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {

    public static final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"; //Key bảo mật
    public static final int TOKEN_EXPIRATION = 30 * 60 * 1000; // Thời gian hết hạn: 30 phút sau khi tạo

    /**
     * Tạo ra JWT từ thông tin user
     * Trả về từ phương thức là chuỗi JWT đã được tạo, chứa thông tin về người dùng và các
     * thông tin thời gian (phát hành, hết hạn)
     * */
    public String generateToken(String username){
        //Thời gian
        Date dateNow = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis() + this.TOKEN_EXPIRATION);

        return Jwts.builder()
                .setSubject(username) //Xác định subject của JWT -> username
                .setIssuedAt(dateNow) // Xác định thời gian mà JWT được tạo
                .setExpiration(expirationDate) // Xác định thời điểm hết hạn
                .signWith(getSignWithKey(), SignatureAlgorithm.HS256) //Chọn thuật thoát để ký và xác thực JWT
                .compact();
    }

    /**
     * Tạo ra một khóa để sử dụng trong việc ký (sign) chuỗi JWT
     * bằng thuật toán HMAC-SHA256
     * */
    private Key getSignWithKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
