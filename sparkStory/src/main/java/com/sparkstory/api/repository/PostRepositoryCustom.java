package com.sparkstory.api.repository;

import com.sparkstory.api.domain.Post;
import com.sparkstory.api.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
