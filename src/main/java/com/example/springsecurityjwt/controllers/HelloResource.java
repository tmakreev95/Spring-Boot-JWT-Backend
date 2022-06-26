package com.example.springsecurityjwt.controllers;


import com.example.springsecurityjwt.models.post.like.Like;
import com.example.springsecurityjwt.repositories.PostRepository;
import com.example.springsecurityjwt.repositories.CategoryRepository;
import com.example.springsecurityjwt.repositories.LikeRepository;
import com.example.springsecurityjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LikeRepository likeRepository;

    @RequestMapping(value="/posts",method = RequestMethod.GET)
    public ResponseEntity<Object> postsReport(){
        return new ResponseEntity(postRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/categories",method = RequestMethod.GET)
    public ResponseEntity<Object> categoriesReport(){
        return new ResponseEntity(categoryRepository.findAllActiveCategories(), HttpStatus.OK);
    }

    /* Helpers and debug */
    @RequestMapping(value="/user-by-email/{email}",method = RequestMethod.GET)
    public ResponseEntity<Object> userReport(@PathVariable String email){
        return new ResponseEntity(userRepository.findByEmail(email), HttpStatus.OK);
//        return new ResponseEntity(userDetailsService.loadUserByUsername(email), HttpStatus.OK);
    }

    @RequestMapping(value="/postLikes/{id}",method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Like>> likesByPostId(@PathVariable Long id){
        return new ResponseEntity(likeRepository.findLikesByPostId(id), HttpStatus.OK);
//        return new ResponseEntity(userDetailsService.loadUserByUsername(email), HttpStatus.OK);
    }

    @RequestMapping(value="/liked-posts/{email}",method = RequestMethod.GET)
    public ResponseEntity<Object> postsReport(@PathVariable String email){
        return new ResponseEntity(postRepository.findLikedPosts(email), HttpStatus.OK);
//        return new ResponseEntity(userDetailsService.loadUserByUsername(email), HttpStatus.OK);
    }

    @RequestMapping(value="/post-by-id/{id}",method = RequestMethod.GET)
    public ResponseEntity<Object> userReport(@PathVariable Long id){
        return new ResponseEntity(postRepository.findById(id), HttpStatus.OK);
//        return new ResponseEntity(userDetailsService.loadUserByUsername(email), HttpStatus.OK);
    }

    @RequestMapping(value="/get-user",method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }


    /* Helpers and debug */

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }


}
