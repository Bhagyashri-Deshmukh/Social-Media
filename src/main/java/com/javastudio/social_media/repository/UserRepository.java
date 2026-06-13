package com.javastudio.social_media.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javastudio.social_media.model.Userr;

@Repository
public interface UserRepository extends JpaRepository<Userr, Integer> {

	public Userr getUserByname(String name);
}
