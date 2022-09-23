package com.sparkstory.api.service;

import com.sparkstory.api.domain.Post;
import com.sparkstory.api.repository.PostRepository;
import com.sparkstory.api.request.PostCreate;
import com.sparkstory.api.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //when
        postService.write(postCreate);

        //then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);

        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회하기")
    void test2(){
        //given
        Post requestPost = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(requestPost);

        //when
        PostResponse response = postService.get(requestPost.getId());

        //then
        assertNotNull(response);
        assertEquals("foo", response.getTitle());
        assertEquals("bar", response.getContent());

    }
    
    @Test
    @DisplayName("글 첫페이지 조회")
    void test3(){
        //given
        List<Post> requestPosts = IntStream.range(0, 30)
                .mapToObj(i -> {
                    return Post.builder()
                            .title("번뜩이는 제목 - " + i)
                            .content("번뜩이는 스토리 - " + i)
                            .build();
                })
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);


        //when
        List<PostResponse> posts = postService.getList(1);

        //then
        assertEquals(2L, posts.size());

    }



}
