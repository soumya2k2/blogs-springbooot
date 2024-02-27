package com.springboot.blog.service.impl;

import ch.qos.logback.classic.net.SMTPAppender;
import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getPostService(int pageNo,int pageSize)
    {
        List<Post> postList = postRepository.findAll();

        return postList;



    }

    public Post getPostDetail(Long id){
        Optional<Post> getPost=postRepository.findById(id);
        if(getPost.isPresent())
        {
            return getPost.get();
        }
        else
        {
            throw new RuntimeException("Post not available");
        }
    }


    @Override
    public Post createPost(PostDTO requestPost){
        Post post=new Post();
        post.setTitle(requestPost.getTitle());
        post.setDescription(requestPost.getDescription());
        post.setContent(requestPost.getContent());
        Post newpost=postRepository.save(post);
        return newpost;
    }

    @Override
    public void deletePost(Long id){
        Optional<Post> post=postRepository.findById(id);
        if(post.isPresent())
        {
            postRepository.deleteById(id);
        }
    }
    @Override
    public Post updatePost(PostDTO requestPost,Long id)
    {
        Optional<Post> getpost=postRepository.findById(id);
        if(getpost.isPresent())
        {
            Post post=new Post();
            post.setTitle(requestPost.getTitle());
            post.setDescription(requestPost.getDescription());
            post.setContent(requestPost.getContent());
            Post newPost=postRepository.save(post);
            return newPost;
        }
        else {
            throw new RuntimeException("Post not available");
        }
    }
}
