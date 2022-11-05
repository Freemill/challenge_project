package com.sparkstory.api.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostEditor {
    public String title = null;
    public String content = null;

    @Builder
    public PostEditor(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
