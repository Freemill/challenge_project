package com.sparkstory.api.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparkstory.api.domain.Post;
import com.sparkstory.api.request.PostSearch;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.sparkstory.api.domain.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Post> getList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(post)
                .limit(postSearch.getSize())
                .offset((long) (postSearch.getOffset()) * postSearch.getSize())
                .orderBy(post.id.desc())
                .fetch();
    }
}
