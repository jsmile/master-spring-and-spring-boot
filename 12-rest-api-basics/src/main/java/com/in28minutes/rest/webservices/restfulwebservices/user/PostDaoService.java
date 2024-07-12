package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.in28minutes.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostDaoService {
   private final PostRepository postRepository;
   private final UserRepository userRepository;

   @Autowired
   public PostDaoService(PostRepository postRepository, UserRepository userRepository) {
      this.postRepository = postRepository;
      this.userRepository = userRepository;
   }

   @Transactional
   public Post updatePost(int id, PostUpdateDTO updateDTO) {
      Post postToUpdate = postRepository.findById(id)
            .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));

      if ( updateDTO.getDescription() != null) {
         postToUpdate.setDescription( updateDTO.getDescription());
      }
      if ( updateDTO.getUserId() != null) {

         User selectedUser = userRepository.findById( updateDTO.getUserId() )
               .orElseThrow(() -> new UserNotFoundException("User does not exist with id: " + updateDTO.getUserId() ));

         postToUpdate.setUser( selectedUser );
      }

      int updatedCount = postRepository.updatePostSelectively( postToUpdate );

      if (updatedCount == 0) {
         throw new PostNotFoundException("Failed to update post with id: " + id);
      }

      return postToUpdate;
   }
}
