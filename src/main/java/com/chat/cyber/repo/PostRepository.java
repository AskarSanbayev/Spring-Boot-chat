package com.chat.cyber.repo;

import com.chat.cyber.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    void deleteByUuid(String uuid);

    Optional<Post> findByUuid(String uuid);

    List<Post> findByAuthorUuid(String authorUuid);
}
