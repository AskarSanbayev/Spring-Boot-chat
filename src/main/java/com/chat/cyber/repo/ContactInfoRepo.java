package com.chat.cyber.repo;

import com.chat.cyber.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInfoRepo extends JpaRepository<ContactInfo, Long> {
}
