package com.chat.cyber.repo;

import com.chat.cyber.model.RefsValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefsValuesRepo extends JpaRepository<RefsValues, Long> {
    List<RefsValues> findByRefsCodeName(String codeName);
}
