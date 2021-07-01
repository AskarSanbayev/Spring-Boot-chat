package com.chat.cyber.dto.request;

import com.chat.cyber.model.enums.UserLikeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLikeDto {

    private Long postId;
    private Long commentId;
    private UserLikeType userLikeType;
}
