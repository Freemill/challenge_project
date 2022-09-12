package com.sparkstory.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


@Setter
@Getter
@ToString
public class PostCreate {


    @NotBlank(message = "타이틀 입력은 필수입니다.")
    private String title;

    @NotBlank(message = "내용 입력은 필수입니다.")
    private String content;

    public PostCreate() {
    }

    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //빌더의 장점
    // - 가독성에 좋다.
    // - 필요한 값만 받을 수 있다. // -> 오버로딩 가능한 조건 찾아보세요
    // - 객체의 불변성

}
