package com.mini.springcloud.eureka.client.auth.user;

import com.mini.springcloud.eureka.client.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
