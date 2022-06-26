package com.example.springsecurityjwt.admin.user.delete.controller;

import com.example.springsecurityjwt.admin.user.delete.dto.DeleteUserRequest;
import com.example.springsecurityjwt.admin.user.delete.dto.DeleteUserResponse;
import com.example.springsecurityjwt.admin.user.delete.service.DeleteUserService;
import com.example.springsecurityjwt.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeleteUserResource {
    @Autowired
    private DeleteUserService deleteUserService;

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value="/admin/user/delete", method = RequestMethod.DELETE)
    public DeleteUserResponse deleteUser(@RequestBody DeleteUserRequest deleteRequest) throws Exception {
        return deleteUserService.deleteUser(deleteRequest);
    }
}
