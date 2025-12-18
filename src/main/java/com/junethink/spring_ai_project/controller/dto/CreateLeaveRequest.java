package com.junethink.spring_ai_project.controller.dto;

import java.time.LocalDate;

public class CreateLeaveRequest {
    private LocalDate leaveDate;

    public CreateLeaveRequest() {
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }
}

