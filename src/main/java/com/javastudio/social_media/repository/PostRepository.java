package com.javastudio.social_media.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.javastudio.social_media.model.Post;


public interface PostRepository extends JpaRepository<Post, Integer> {

	

}
