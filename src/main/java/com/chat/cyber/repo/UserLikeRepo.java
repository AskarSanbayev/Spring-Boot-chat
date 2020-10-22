package com.chat.cyber.repo;

import com.chat.cyber.model.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikeRepo extends JpaRepository<UserLike,Long> {
}
