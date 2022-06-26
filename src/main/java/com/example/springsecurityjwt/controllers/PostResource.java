package com.example.springsecurityjwt.controllers;

import com.example.springsecurityjwt.dto.common.LikePostRequest;
import com.example.springsecurityjwt.dto.common.LikePostResponse;
import com.example.springsecurityjwt.models.post.Post;
import com.example.springsecurityjwt.dto.post.PostRequest;
import com.example.springsecurityjwt.dto.post.PostResponse;
import com.example.springsecurityjwt.dto.post.PostUpdateRequest;
import com.example.springsecurityjwt.dto.post.PostUpdateResponse;
import com.example.springsecurityjwt.repositories.LikeRepository;
import com.example.springsecurityjwt.services.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class PostResource {
    @Autowired
    private PostService postService;

    @Autowired
    private LikeRepository likeRepository;

    @RequestMapping(value="/user/add/post", method = RequestMethod.POST)
    public PostResponse registerPost(@RequestBody PostRequest postRequest, HttpServletRequest httpServletRequest) throws Exception {
        return postService.registerPost(postRequest, httpServletRequest);
    }

    @RequestMapping(value="/user/delete/post/{id}",method = RequestMethod.DELETE)
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }

    @RequestMapping(value = "/user/update/post", method = RequestMethod.PUT)
    public PostUpdateResponse updatePost (@RequestBody PostUpdateRequest postUpdateRequest) {
        return postService.updatePost(postUpdateRequest);
    }

    @RequestMapping(value = "/user/posts", method = RequestMethod.POST)
    public ResponseEntity<ArrayList<Post>> getUserPosts(){
        return postService.getPosts();
    }

    @RequestMapping(value = "/user/post/like", method = RequestMethod.POST)
    public LikePostResponse likePost(@RequestBody LikePostRequest likePostRequest, HttpServletRequest httpServletRequest) {
        return postService.likePost(httpServletRequest, likePostRequest);
    }
}
