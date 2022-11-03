package com.sparkstory.api.controller;

import com.sparkstory.api.domain.Post;
import com.sparkstory.api.request.PostCreate;
import com.sparkstory.api.response.PostResponse;
import com.sparkstory.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 글 등록, Post Method
    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request) throws Exception {
        //db.save(param)
        postService.write(request);
    }


    // 글 1개 조회
    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(Pageable pageable) {
        return postService.getList(pageable);
    }
}
