package com.chat.cyber.dto.request.userinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InterestsDto extends BaseUserInfoDto {
    private String activities;
    private String aboutInterests;
    private String favoriteMusic;
    private String favoriteMovies;
    private String favoriteShows;
    private String favoriteBooks;
    private String favoriteQuotes;
    private String aboutMe;
}
