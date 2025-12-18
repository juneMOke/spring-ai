package com.junethink.spring_ai_project.service;

import com.junethink.spring_ai_project.entities.EmployeeLeave;
import com.junethink.spring_ai_project.entities.UserApp;
import com.junethink.spring_ai_project.repository.EmployeeLeaveRepository;
import com.junethink.spring_ai_project.repository.UserAppRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional
public class EmployeeLeaveService {

    private final EmployeeLeaveRepository leaveRepository;
    private final UserAppRepository userRepository;

    public EmployeeLeaveService(EmployeeLeaveRepository leaveRepository, UserAppRepository userRepository) {
        this.leaveRepository = leaveRepository;
        this.userRepository = userRepository;
    }

    public List<EmployeeLeave> getLeavesForUser(Long userId) {
        Optional<UserApp> userOpt = userRepository.findById(userId);
        return userOpt.map(leaveRepository::findByUserApp).orElseGet(List::of);
    }

    public int getLeaveCountForUser(Long userId) {
        return getLeavesForUser(userId).size();
    }

    public EmployeeLeave createLeaveForUser(Long userId, LocalDate leaveDate) {
        UserApp user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        EmployeeLeave employeeLeave = new EmployeeLeave(leaveDate, user);
        return leaveRepository.save(employeeLeave);
    }
}

