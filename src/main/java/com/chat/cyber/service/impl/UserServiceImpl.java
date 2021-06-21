package com.chat.cyber.service.impl;

import com.chat.cyber.converter.UserDtoConverter;
import com.chat.cyber.dto.PageInfoDto;
import com.chat.cyber.dto.request.RegUserDataDto;
import com.chat.cyber.dto.response.PagePresentDto;
import com.chat.cyber.dto.response.UserDto;
import com.chat.cyber.exception.UnexpectedException;
import com.chat.cyber.model.User;
import com.chat.cyber.repo.UserRepository;
import com.chat.cyber.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.chat.cyber.util.AppConstants.USER_FULLNAME;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserServiceImpl(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByIdAndUuid(Long id, String uuid) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent() || !uuid.equalsIgnoreCase(user.get().getUuid())) {
            throw new UnexpectedException("User not found");
        }
        return user.get();
    }

    @Override
    public PagePresentDto<UserDto> getFriends(Principal principal, PageInfoDto pageInfo) {
        Pageable pageable;
        int size = pageInfo.getSize();
        int page = pageInfo.getPage();
        boolean isDescOrder = pageInfo.isDescOrder();
        Sort sort;
        if (!isDescOrder) sort = Sort.by(USER_FULLNAME).descending();
        else sort = Sort.by(USER_FULLNAME);
        if (size <= 0) {
            pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
        } else {
            pageable = PageRequest.of(page, size, sort);
        }
        if (pageInfo.getSearchText() == null) {
            pageInfo.setSearchText("");
        }
        Slice<User> allFriends = userRepository.getAllFriends(principal.getName(), pageInfo.getSearchText(), pageable);
        Slice<UserDto> response = allFriends.map(userDtoConverter::convert);
        return new PagePresentDto<>(response);
    }

    @Override
    public Optional<User> findByLogin(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUUid(String uuid) {
        return userRepository.findByUuid(uuid);
    }

    @Override
    public void editFriends(Principal principal, Long friendId, String friendUuid, boolean isRemove) {
        Optional<User> userOptional = userRepository.findByUsername(principal.getName());
        Optional<User> friendOptional = userRepository.findById(friendId);
        if (userOptional.isPresent() && friendOptional.isPresent()) {
            User user = userOptional.get();
            User friend = friendOptional.get();
            if (friend.getUuid().equalsIgnoreCase(friendUuid)) {
                if (isRemove) {
                    user.removeFriend(friend);
                } else {
                    user.addFriend(friend);
                }
            }
        }
    }

    @Override
    public void create(RegUserDataDto regUserDataDto) {
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setUsername(regUserDataDto.getLogin());
        user.setEmail(regUserDataDto.getEmail());
        userRepository.save(user);
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException();
    }
}