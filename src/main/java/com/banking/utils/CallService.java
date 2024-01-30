package com.banking.utils;

import com.banking.dto.LichSuGiaoDichDTO;
import com.banking.dto.LichSuGiaoDichEncyp;
import com.banking.service.LichSuGiaoDichSer;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallService {
    private static final Logger logger = LoggerFactory.getLogger(CallService.class);

    public static void callService(String url, LichSuGiaoDichEncyp lichSuGiaoDichDTO) throws IOException {
        try {   // Chuyển đối tượng thành JSON sử dụng Gson
            Gson gson = new Gson();
            String jsonInputString = gson.toJson(lichSuGiaoDichDTO);

            // Mở kết nối đến URL
            URL ur = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) ur.openConnection();

            // Thiết lập phương thức HTTP (POST, PUT, etc.)
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.writeBytes(jsonInputString);
                wr.flush();
            }

            // Đọc phản hồi từ API
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Hiển thị kết quả
                System.out.println("API Response: " + response);
            } else {
                logger.error("Error processing transaction. TransactionID: {}, Account: {}, InDebt: {}, Have: {}, Time: {}",
                        redactSensitiveInfo(lichSuGiaoDichDTO.getTran()),
                        redactSensitiveInfo(lichSuGiaoDichDTO.getAccDi()),
                        redactSensitiveInfo(lichSuGiaoDichDTO.getIntDebt() + ""),
                        redactSensitiveInfo(lichSuGiaoDichDTO.getHave() + ""));            }

            // Đóng kết nối
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String redactSensitiveInfo(String sensitiveInfo) {
        // Logic che giấu thông tin nhạy cảm ở đây, có thể thay thế bằng dấu '?'
        return "?";
    }
}
