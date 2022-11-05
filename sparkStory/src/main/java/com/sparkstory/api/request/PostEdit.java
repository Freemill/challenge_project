package com.sparkstory.api.request;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostEdit {

    @NotBlank(message = "타이틀 입력은 필수입니다.")
    private String title;

    @NotBlank(message = "내용 입력은 필수입니다.")
    private String content;

    @Builder
    public PostEdit(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
