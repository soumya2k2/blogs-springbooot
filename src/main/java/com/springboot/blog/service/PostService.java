package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDTO;

import java.util.List;

public interface PostService {
    List<Post> getPostService(int pageNo,int pageSize);
    Post getPostDetail(Long id);
    Post createPost(PostDTO requestPost);
    Post updatePost(PostDTO requestPost,Long id);
    void deletePost(Long id);
}
