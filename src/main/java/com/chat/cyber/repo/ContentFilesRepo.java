package com.chat.cyber.repo;

import com.chat.cyber.model.ContentFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContentFilesRepo extends JpaRepository<ContentFile, UUID> {

    ContentFile findByUserIdAndAndContentTypeCodeName(Long userId, String codeName);
}
