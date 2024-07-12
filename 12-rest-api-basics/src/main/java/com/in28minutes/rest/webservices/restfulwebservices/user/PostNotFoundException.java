package com.in28minutes.rest.webservices.restfulwebservices.user;

public class PostNotFoundException extends RuntimeException {
   public PostNotFoundException(String message) {
      super(message);
   }
}
