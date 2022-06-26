package com.example.springsecurityjwt.admin.category.controller;

import com.example.springsecurityjwt.admin.category.dto.AdminCategoryAddRequest;
import com.example.springsecurityjwt.admin.category.dto.AdminCategoryAddResponse;
import com.example.springsecurityjwt.admin.category.dto.AdminCategoryUpdateRequest;
import com.example.springsecurityjwt.admin.category.dto.AdminCategoryUpdateResponse;
import com.example.springsecurityjwt.admin.category.service.AdminCategoryService;
import com.example.springsecurityjwt.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminCategoryResource {

    @Autowired
    AdminCategoryService adminCategoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(value="/admin/category/add", method = RequestMethod.POST)
    public AdminCategoryAddResponse addCategory(@RequestBody AdminCategoryAddRequest adminCategoryAddRequest) throws Exception {
        return adminCategoryService.registerCategory(adminCategoryAddRequest);
    }

    @RequestMapping(value="/admin/category/update", method = RequestMethod.PUT)
    public AdminCategoryUpdateResponse updateCategory(@RequestBody AdminCategoryUpdateRequest adminCategoryUpdateRequest) throws Exception {
        return adminCategoryService.updateCategory(adminCategoryUpdateRequest);
    }

    @RequestMapping(value="/admin/category/delete/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable Long id) { adminCategoryService.deleteCategory(id); }

}
