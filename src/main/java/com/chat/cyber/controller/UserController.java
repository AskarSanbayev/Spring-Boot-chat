package com.chat.cyber.controller;

import com.chat.cyber.dto.PageInfoDto;
import com.chat.cyber.dto.request.userinfo.BaseUserInfoDto;
import com.chat.cyber.model.User;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.service.AdditionalUserInfoService;
import com.chat.cyber.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;

@Api(value = "Страница пользователя", tags = {"Страница пользователя"})
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdditionalUserInfoService additionalUserInfoService;

    @ApiOperation(value = "Получение всех пользователей", notes = "Получение всех пользователей")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @ApiOperation(value = "Получение друзей", notes = "Получение друзей")
    @GetMapping("/friends")
    public Slice<User> findAllFriends(@ApiIgnore Principal principal,
                                      @ApiParam(value = "Текст поиска", required = false) @RequestParam(required = false) String searchText,
                                      @ApiParam(value = "Страница (нумерация с 0)", example = "0") @RequestParam(name = "page") Integer page,
                                      @ApiParam(value = "Строк", example = "2") @RequestParam(name = "size") Integer size,
                                      @ApiParam(value = "Признак По убыванию", example = "false") @RequestParam(name = "isdesc", required = false) Boolean isdesc) {
        PageInfoDto pageInfo = PageInfoDto.builder()
                .page(page)
                .size(size)
                .searchText(searchText)
                .isDescOrder(isdesc != null)
                .build();
        return userService.getFriends(principal, pageInfo);
    }

    @ApiOperation(value = "Обновление списка друзей", notes = "Обновление списка")
    @PutMapping("/friends")
    public void editFriends(@ApiIgnore Principal principal,
                            @ApiParam(value = "Uuid друга") @RequestParam(name = "uuid") String friendUuid,
                            @ApiParam(value = "Ид друга") @RequestParam(name = "id") Long friendId,
                            @ApiParam(value = "Удаление/добавление") @RequestParam(name = "isRemove") boolean isRemove) {
        userService.editFriends(principal, friendId, friendUuid, isRemove);
    }

    @ApiOperation(value = "Получение пользователя по UUID", notes = "Получение пользователя по UUID")
    @GetMapping("/{uuid}")
    public User findUser(@PathVariable("uuid") String uuid) {
        return userService.findByUUid(uuid).orElse(null);
    }


    @PutMapping("/info")
    public void addAdditionalUserInfo(@ApiIgnore Principal principal, @RequestParam(name = "codeName") RefsCodeName
            refsCodeName,
                                      @RequestBody List<BaseUserInfoDto> baseUserInfoDto) {
        additionalUserInfoService.save(refsCodeName, baseUserInfoDto, principal);
    }
}
