package com.banking.repository;

import com.banking.entity.LichSuGiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILichSuGiaoDich extends JpaRepository<LichSuGiaoDich, Long> {
}
