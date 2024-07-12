package com.in28minutes.rest.webservices.restfulwebservices.user;

public class PostUpdateDTO {
   private String description;
   private Integer userId;

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Integer getUserId() {
      return userId;
   }

   public void setUserId(Integer userId) {
      this.userId = userId;
   }

   @Override
   public String toString() {
      return "PostUpdateDTO{" +
            ", description='" + description + '\'' +
            ", userId=" + userId +
            '}';
   }
}
