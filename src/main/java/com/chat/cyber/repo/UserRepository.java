package com.chat.cyber.repo;

import com.chat.cyber.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(value = "USER.base")
    Optional<User> findByUsername(String username);

    @EntityGraph(value = "USER.base")
    Optional<User> findByEmail(String email);

    @EntityGraph(value = "USER.base")
    Optional<User> findByUuid(String uuid);

    @EntityGraph(value = "USER.card")
    Optional<User> findCardByUsername(String username);

    @Query(value = "SELECT friends FROM User me" +
            " join me.friendList friends " +
            " where friends.fullName like %:fullName% and me.username =:userName")
    Slice<User> getAllFriends(@Param("userName") String username,
                              @Param("fullName") String fullName, Pageable pageable);

}
