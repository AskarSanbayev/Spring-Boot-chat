package com.chat.cyber.repo;

import com.chat.cyber.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUuid(String uuid);

    @Query(value = "SELECT friends FROM User me" +
            " inner join me.friendList friends " +
            " where friends.fullName like %:fullName% and me.username =:userName")
    Slice<User> getAllFriends(@Param("userName") String username,
                              @Param("fullName") String fullName, Pageable pageable);

}
