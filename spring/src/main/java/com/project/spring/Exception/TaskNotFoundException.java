package com.project.spring.Exception;

public class TaskNotFoundException extends RuntimeException   {
   public TaskNotFoundException(String message){
       super(message);
   }
}
