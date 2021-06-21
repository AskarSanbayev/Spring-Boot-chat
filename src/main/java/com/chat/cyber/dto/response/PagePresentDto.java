package com.chat.cyber.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PagePresentDto<T> {

    private List<T> content;
    private boolean hasNext;

    public PagePresentDto(Slice<T> page) {
        this(page.getContent(), page.hasNext());
    }

    public PagePresentDto(List<T> content, boolean hasNext) {
        this.content = content;
        this.hasNext = hasNext;
    }
}