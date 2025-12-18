package com.junethink.spring_ai_project.repository;

import com.junethink.spring_ai_project.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Long> {
}

