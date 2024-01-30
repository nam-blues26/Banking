package com.banking.controller;

import com.banking.dto.LichSuGiaoDichDTO;
import com.banking.dto.LichSuGiaoDichEncyp;
import com.banking.service.LichSuGiaoDichSer;
import com.banking.utils.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("giao-dich")
public class LichSuController {
    @Autowired
    private LichSuGiaoDichSer lichSuGiaoDichSer;

    @PostMapping("add")
    public ResponseEntity add(@RequestBody LichSuGiaoDichDTO lichSuGiaoDichDTO) throws IOException {
        LichSuGiaoDichEncyp lichSuGiaoDichEncyp = lichSuGiaoDichSer.EncypParameter(lichSuGiaoDichDTO);
        CallService.callService("http://localhost:8000/giao-dich/decyp", lichSuGiaoDichEncyp);
        return ResponseEntity.ok().body("susscess");
    }

    @PostMapping("decyp")
    public ResponseEntity dectypRSA(@RequestBody LichSuGiaoDichEncyp lichSuGiaoDich) {
        lichSuGiaoDichSer.add(lichSuGiaoDichSer.DEcypParameter(lichSuGiaoDich));
        return ResponseEntity.ok().body("susscess");
    }
}
