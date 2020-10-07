package com.chat.cyber.repo;

import com.chat.cyber.model.CareerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerInfoRepo extends JpaRepository<CareerInfo, Long> {
}
