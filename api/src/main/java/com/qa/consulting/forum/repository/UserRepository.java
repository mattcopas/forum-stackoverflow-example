package com.qa.consulting.forum.repository;

import com.qa.consulting.forum.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Matt on 12/07/2017.
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByUsername(String username);
}
