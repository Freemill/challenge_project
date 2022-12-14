package com.sparkstory.api.service;

import com.sparkstory.api.TestConfig;
import com.sparkstory.api.domain.Post;
import com.sparkstory.api.repository.PostRepository;
import com.sparkstory.api.request.PostCreate;
import com.sparkstory.api.request.PostEdit;
import com.sparkstory.api.request.PostSearch;
import com.sparkstory.api.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Import(TestConfig.class)
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
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
    void test2() {
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
    void test3() {
        //given
        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> {
                    return Post.builder()
                            .title("번뜩이는 제목 " + i)
                            .content("번뜩이는 스토리 " + i)
                            .build();
                })
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .build();


        //when
        List<PostResponse> posts = postService.getList(postSearch);

        //then
        assertEquals(10L, posts.size());
        assertEquals("번뜩이는 제목 19", posts.get(0).getTitle());

    }

    @Test
    @DisplayName("글 제목 수정")
    void test4() {
        //given
        Post post = Post.builder()
                .title("번뜩이는 제목")
                .content("번뜩이는 내용")
                .build();

        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("엄청 번뜩이는 제목")
                .content("엄청 번뜩이는 내용")
                .build();

        //when
        postService.edit(post.getId(), postEdit);

        //then
        Post changedPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("존재하지 않은 글입니다. id=" + post.getId()));
        assertEquals("엄청 번뜩이는 제목", changedPost.getTitle());
        assertEquals("엄청 번뜩이는 내용", changedPost.getContent());

    }

    @Test
    @DisplayName("게시글 삭제")
    void test5(){
        //given
        Post post = Post.builder()
                .title("번뜩이는 제목")
                .content("번뜩이는 내용")
                .build();

        postRepository.save(post);

        //when
        postService.delete(post.getId());

        assertEquals(0, postRepository.count());
    }


}
