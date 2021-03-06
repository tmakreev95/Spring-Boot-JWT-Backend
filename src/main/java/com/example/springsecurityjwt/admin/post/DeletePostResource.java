package com.example.springsecurityjwt.admin.post;

import com.example.springsecurityjwt.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeletePostResource {

    @Autowired
    PostRepository postRepository;

    @RequestMapping(value="/admin/delete/post/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        postRepository.deleteById(id);
    }
}
