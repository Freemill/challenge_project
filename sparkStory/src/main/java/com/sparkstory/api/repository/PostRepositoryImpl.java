package com.sparkstory.api.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparkstory.api.domain.Post;
import com.sparkstory.api.domain.QPost;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Post> getLis(int page) {
        return jpaQueryFactory.selectFrom(QPost.post)
                .limit(10)
                .offset((long)(page - 1) * 10)
                .fetch();
    }
}
