package com.chat.cyber.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageInfoDto {
    private int page;
    private int size;
    private String searchText;
    private boolean isDescOrder;
}
