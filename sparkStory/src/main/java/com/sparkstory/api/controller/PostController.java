package com.sparkstory.api.controller;

import com.sparkstory.api.domain.Post;
import com.sparkstory.api.request.PostCreate;
import com.sparkstory.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/posts/{postId}")
    public Post get(@PathVariable(name = "postId") Long id) {
        Post post = postService.get(id);

        return post;
    }
}
