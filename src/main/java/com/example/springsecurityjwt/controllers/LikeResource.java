package com.example.springsecurityjwt.controllers;

import com.example.springsecurityjwt.models.post.like.Like;
import com.example.springsecurityjwt.dto.common.LikesRequest;
import com.example.springsecurityjwt.repositories.LikeRepository;
import com.example.springsecurityjwt.services.post.like.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LikeResource {
    @Autowired
    private LikeService likeService;

    @Autowired
    private LikeRepository likeRepository;

    @RequestMapping(value="/user/post/likes", method = RequestMethod.POST)
    public ResponseEntity<ArrayList<Like>> likesByAdId(@RequestBody LikesRequest likesRequest){
        return new ResponseEntity(likeRepository.findLikesByPostId(likesRequest.getId()), HttpStatus.OK);
    }

}
