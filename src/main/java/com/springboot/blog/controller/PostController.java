package com.springboot.blog.controller;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDTO requestPost){
        Post responsePost=postService.createPost(requestPost);
        return new ResponseEntity<>(responsePost, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPost(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize)
    {
        List<Post> postList=postService.getPostService(pageNo,pageSize);
        if(postList.size()>0)
            return new ResponseEntity<>(postList,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostDetail(@PathVariable(name = "id")Long postId)
    {
        Post post = postService.getPostDetail(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable(name="id")Long postId)
    {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@RequestBody PostDTO postDto,@PathVariable(name="id") Long postId )
    {
        Post postResponseDto=postService.updatePost(postDto,postId);
        return new ResponseEntity<>(postResponseDto,HttpStatus.OK);
    }
}
