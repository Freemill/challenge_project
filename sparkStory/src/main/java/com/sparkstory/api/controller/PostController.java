package com.sparkstory.api.controller;

import com.sparkstory.api.request.PostCreate;
import com.sparkstory.api.request.PostEdit;
import com.sparkstory.api.request.PostSearch;
import com.sparkstory.api.response.PostResponse;
import com.sparkstory.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        postService.edit(postId, request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
