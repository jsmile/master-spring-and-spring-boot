package com.in28minutes.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.rest.webservices.restfulwebservices.user.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Integer> {
   @Query("UPDATE Post p SET " +
         "p.description = CASE WHEN :#{#post.description} IS NULL THEN p.description ELSE :#{#post.description} END, " +
         "p.user = CASE WHEN :#{#post.user} IS NULL THEN p.user ELSE :#{#post.user} END " +
         "WHERE p.id = :#{#post.id}")
   int updatePostSelectively(@Param("post") Post post);
}
