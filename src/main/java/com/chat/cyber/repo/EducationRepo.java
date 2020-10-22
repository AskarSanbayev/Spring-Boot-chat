package com.chat.cyber.repo;

import com.chat.cyber.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface EducationRepo extends JpaRepository<Education, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Education e WHERE e.id NOT IN (:ids) AND e.user.id = :userid")
    void deleteOldCareerInfos(@Param("ids") Iterable<Long> infoIds, @Param("userid") Long userId);
}
