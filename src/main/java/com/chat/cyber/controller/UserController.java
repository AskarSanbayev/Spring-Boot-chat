package com.chat.cyber.controller;

import com.chat.cyber.dto.request.UserLikeDto;
import com.chat.cyber.dto.request.userinfo.BaseUserInfoDto;
import com.chat.cyber.model.User;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.service.AdditionalUserInfoService;
import com.chat.cyber.service.UserLikeService;
import com.chat.cyber.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Api(value = "Страница пользователя", tags = {"Страница пользователя"})
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserLikeService userLikeService;
    @Autowired
    private AdditionalUserInfoService additionalUserInfoService;

    @ApiOperation(value = "Получение всех пользователей", notes = "Получение всех пользователей")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @ApiOperation(value = "Получение друзей", notes = "Получение друзей")
    @GetMapping("/{uuid}/friends")
    public List<User> findAllFriends(@PathVariable("uuid") String uuid) {
        Optional<User> user = userService.findByUUid(uuid);
        return user.isPresent() ? user.get().getFriendList() : Collections.emptyList();
    }

    @ApiOperation(value = "Получение пользователя по UUID", notes = "Получение пользователя по UUID")
    @GetMapping("/{uuid}")
    public User findUser(@PathVariable("uuid") String uuid) {
        return userService.findByUUid(uuid).orElse(null);
    }

    @PutMapping("/like")
    public void updateLikeAndDislike(@ApiIgnore Principal principal, @RequestBody UserLikeDto userLikeDto) {
        userLikeService.update(userLikeDto, principal);
    }

    @PutMapping("/info")
    public void addAdditionalUserInfo(@ApiIgnore Principal principal, @RequestParam(name = "codeName") RefsCodeName refsCodeName,
                                      @RequestBody List<BaseUserInfoDto> baseUserInfoDto) {
        additionalUserInfoService.save(refsCodeName, baseUserInfoDto, principal);
    }
}
