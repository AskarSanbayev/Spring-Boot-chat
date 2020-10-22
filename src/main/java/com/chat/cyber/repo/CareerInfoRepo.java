package com.chat.cyber.repo;

import com.chat.cyber.model.CareerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CareerInfoRepo extends JpaRepository<CareerInfo, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM CareerInfo career WHERE career.id NOT IN (:ids) AND career.user.id = :userid")
    void deleteOldCareerInfos(@Param("ids") Iterable<Long> infoIds, @Param("userid") Long userId);
}
