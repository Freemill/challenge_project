package com.sparkstory.api.repository;

import com.sparkstory.api.domain.Post;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getLis(int page);
}
