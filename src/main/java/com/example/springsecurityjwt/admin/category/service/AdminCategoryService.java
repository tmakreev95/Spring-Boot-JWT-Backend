package com.example.springsecurityjwt.admin.category.service;

import com.example.springsecurityjwt.admin.category.dto.AdminCategoryAddRequest;
import com.example.springsecurityjwt.admin.category.dto.AdminCategoryAddResponse;
import com.example.springsecurityjwt.admin.category.dto.AdminCategoryUpdateRequest;
import com.example.springsecurityjwt.admin.category.dto.AdminCategoryUpdateResponse;
import com.example.springsecurityjwt.enums.CategoryStatus;
import com.example.springsecurityjwt.models.post.Post;
import com.example.springsecurityjwt.models.post.category.Category;
import com.example.springsecurityjwt.repositories.CategoryRepository;
import com.example.springsecurityjwt.repositories.PostRepository;
import com.example.springsecurityjwt.services.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    public AdminCategoryAddResponse registerCategory(AdminCategoryAddRequest adminCategoryAddRequest) {
        AdminCategoryAddResponse response = new AdminCategoryAddResponse();

        if (categoryRepository.findExistingCategoryByName(adminCategoryAddRequest.getName())) {
            response.setMessage("The desired category already exists!");
            response.setStatus(Boolean.FALSE);
        }
        else {
            try {
                Category category = new Category(adminCategoryAddRequest.getName(), adminCategoryAddRequest.getDescription(), CategoryStatus.ACTIVE);
                categoryRepository.save(category);

                response.setMessage("Category added successfully!");
                response.setStatus(Boolean.TRUE);
            }
            catch (Exception e){
                response.setMessage("Error while adding category!");
                response.setStatus(Boolean.FALSE);
            }
        }
        return response;
    }

    public AdminCategoryUpdateResponse updateCategory(AdminCategoryUpdateRequest adminCategoryUpdateRequest) {
        AdminCategoryUpdateResponse adminCategoryUpdateResponse = new AdminCategoryUpdateResponse();

        Category category = categoryRepository.findCategoryById(adminCategoryUpdateRequest.getId());

        try {
            if (adminCategoryUpdateRequest.getName() != null && adminCategoryUpdateRequest.getDescription() != null) {
                category.setName(adminCategoryUpdateRequest.getName());
                category.setDescription(adminCategoryUpdateRequest.getDescription());

                categoryRepository.save(category);

                adminCategoryUpdateResponse.setMessage("Category updated successfully!");
                adminCategoryUpdateResponse.setStatus(Boolean.TRUE);
            }
        }
        catch (Exception e) {
            adminCategoryUpdateResponse.setMessage("Error while updating category with id: " + adminCategoryUpdateRequest.getId());
            adminCategoryUpdateResponse.setStatus(Boolean.FALSE);
        }

        return adminCategoryUpdateResponse;
    }

    public void deleteCategory(Long id) {
        for(Post post : postRepository.findPostByCategoryId(id)){
            if(post.getCategories().size() == 1 &&
               post.getCategories().contains(categoryRepository.findCategoryById(id))) {
                post.getCategories().remove(categoryRepository.findCategoryById(id));
                postService.deletePost(post.getId());
            }
            else {
                post.getCategories().remove(categoryRepository.findCategoryById(id));
                postRepository.save(post);
            }
        }

        categoryRepository.deleteById(id);
    }
}
