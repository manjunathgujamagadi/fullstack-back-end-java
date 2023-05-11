package com.springmanju.fullstackback.repository;

import com.springmanju.fullstackback.model.User;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositery extends JpaRepository<User,Long>{

}
