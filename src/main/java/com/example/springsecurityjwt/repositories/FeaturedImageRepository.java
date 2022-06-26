package com.example.springsecurityjwt.repositories;

import com.example.springsecurityjwt.models.post.featured_image.FeaturedImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeaturedImageRepository extends JpaRepository<FeaturedImage, Long> {

}
