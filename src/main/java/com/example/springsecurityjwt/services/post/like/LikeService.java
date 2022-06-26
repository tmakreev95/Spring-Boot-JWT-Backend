package com.example.springsecurityjwt.services.post.like;

import com.example.springsecurityjwt.repositories.LikeRepository;
import com.example.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private JwtUtil jwtTokenUtil;

//    public LikeResponse advertisementLikesReport(LikesRequest likesRequest) {
//        LikeResponse response = new LikeResponse();
//
//
//
//        return response;
//    }

}
