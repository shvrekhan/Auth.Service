package com.AuthService.Repository;

import com.AuthService.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UsersRepo extends JpaRepository<UserDetail,String> {
    UserDetail findByEmail(String email);
}
