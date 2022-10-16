package com.example.webApp.repositories;

import com.example.webApp.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long>{
    UserModel findByEmail(String email);

}
