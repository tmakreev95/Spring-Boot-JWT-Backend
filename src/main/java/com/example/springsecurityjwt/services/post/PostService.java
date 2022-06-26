package com.example.springsecurityjwt.services.post;

import com.example.springsecurityjwt.enums.PostStatus;
import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.models.post.Post;
import com.example.springsecurityjwt.models.post.category.Category;
import com.example.springsecurityjwt.dto.post.PostRequest;
import com.example.springsecurityjwt.dto.post.PostResponse;
import com.example.springsecurityjwt.dto.post.PostUpdateRequest;
import com.example.springsecurityjwt.dto.post.PostUpdateResponse;
import com.example.springsecurityjwt.models.post.like.Like;
import com.example.springsecurityjwt.dto.common.LikePostRequest;
import com.example.springsecurityjwt.dto.common.LikePostResponse;
import com.example.springsecurityjwt.models.post.featured_image.FeaturedImage;
import com.example.springsecurityjwt.repositories.PostRepository;
import com.example.springsecurityjwt.repositories.CategoryRepository;
import com.example.springsecurityjwt.repositories.LikeRepository;
import com.example.springsecurityjwt.repositories.UserRepository;
import com.example.springsecurityjwt.util.JwtUtil;
import com.example.springsecurityjwt.util.FeaturedImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class PostService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private FeaturedImageUtil featuredImageUtil;

    public PostResponse registerPost(PostRequest postRequest, HttpServletRequest httpServletRequest) throws Exception {
        PostResponse response = new PostResponse();

        String token = httpServletRequest.getHeader("Authorization").substring(7);
        final String userName = jwtTokenUtil.extractUsername(token);

        User author = userRepository.findUserByEmail(userName);
        Set<Category> categories = new HashSet<>();

        for (String catName : postRequest.getCategories()) {
            if(categoryRepository.findExistingCategoryByName(catName)) {
                Category category = categoryRepository.findByName(catName);
                categories.add(category);
            }
            else {
                response.setMessage("The desired category does not exist! Choose another category!");
                response.setStatus(Boolean.FALSE);
                break;
            }
        }

        try {
            if(postRepository.findExistingPostByTitle(postRequest.getTitle())) {
                response.setMessage("Post with title(" + postRequest.getTitle() + ") exists!");
                response.setStatus(Boolean.FALSE);
            }
            else {
                Post post = new Post(postRequest.getTitle(), postRequest.getDescription(),
                        author, PostStatus.PUBLISHED ,categories);

                Map<String, String> config = featuredImageUtil.persistImage(postRequest.getFeaturedImageContents(),
                        postRequest.getFeaturedImageName());

                FeaturedImage featuredImage = new FeaturedImage(config.get("thumbNailName"),
                        postRequest.getFeaturedImageMimeType(), config.get("filePath"));

                featuredImage.setPost(post);
                post.setFeaturedImage(featuredImage);

                postRepository.save(post);

                response.setMessage("Post added successfully!");
                response.setStatus(Boolean.TRUE);
            }
        }
        catch (Exception e) {
            response.setMessage("Error while adding post!Please contact administrator!");
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }

    public void deletePost(Long id) {
        for(Like postLike : likeRepository.findLikesByPostId(id)) {
            postLike.setUser(null);
            postLike.setPost(null);
            likeRepository.delete(postLike);
        }

        postRepository.deleteById(id);
    }

    public ResponseEntity<ArrayList<Post>> getPosts(){
        return  new ResponseEntity<>(postRepository.findAllPublishedPosts(), HttpStatus.OK);
    }

    public LikePostResponse likePost(HttpServletRequest httpServletRequest, LikePostRequest likePostRequest) {
        LikePostResponse response = new LikePostResponse();

        String token = httpServletRequest.getHeader("Authorization").substring(7);
        final String userName = jwtTokenUtil.extractUsername(token);

        User user = userRepository.findUserByEmail(userName);

        Post post = postRepository.findPostById(likePostRequest.getId());

        try {
            if(likeRepository.findExistingLikeByUserEmail(userName, post.getId())) {
                response.setMessage("Post already liked by " + userName);
                response.setStatus(Boolean.FALSE);
            }
            else {
                Set<Like> likes = new HashSet<>(post.getLikes());

                Like like = new Like(user, post);

                likeRepository.save(like);

                response.setMessage("Post successfully liked by " + like.getUser().getEmail() + "");
                response.setStatus(Boolean.TRUE);
            }
        } catch (Exception e) {
            response.setMessage("Error while liking a post! Contact administrator!");
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }

    public PostUpdateResponse updatePost(PostUpdateRequest postUpdateRequest) {
        PostUpdateResponse postUpdateResponse = new PostUpdateResponse();

        try {
            Post post = postRepository.findPostById(postUpdateRequest.getId());

            Set<Category> newCategories = new HashSet<>();

            for(String cat : postUpdateRequest.getCategories()) {
                newCategories.add(categoryRepository.findByName(cat));
            }

            post.setCategories(newCategories);
            post.setTitle(postUpdateRequest.getTitle());
            post.setDescription(postUpdateRequest.getDescription());

            postRepository.save(post);

            postUpdateResponse.setMessage("Post updated successfully!");
            postUpdateResponse.setStatus(Boolean.TRUE);

        }
        catch (Exception e) {
            postUpdateResponse.setMessage("Error while updating post with id: " + postUpdateRequest.getId());
            postUpdateResponse.setStatus(Boolean.FALSE);
        }

        return postUpdateResponse;
    }
}
