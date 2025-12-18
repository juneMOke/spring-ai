package com.junethink.spring_ai_project.repository;

import com.junethink.spring_ai_project.entities.EmployeeLeave;
import com.junethink.spring_ai_project.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Long> {
    List<EmployeeLeave> findByUserApp(UserApp userApp);
}

