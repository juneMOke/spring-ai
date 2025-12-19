package com.junethink.spring_ai_project.tool;

import com.junethink.spring_ai_project.entities.EmployeeLeave;
import com.junethink.spring_ai_project.service.EmployeeLeaveService;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * A tool for managing employee leaves.
 */
@Component
public class EmployeeLeaveTool {

    private final EmployeeLeaveService leaveService;

    public EmployeeLeaveTool(EmployeeLeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @Tool(name = "get_employee_leaves", description = "Recupère la liste des congés d'un user")
    public List<EmployeeLeave> getLeaves(ToolContext context) {
        Long userId = (Long) context.getContext().get("userId");
        return leaveService.getLeavesForUser(userId);
    }

    @Tool(name = "get_employee_leave_count", description = "Recupère le nombre de congés d'un user")
    public int getLeavesCount(ToolContext context) {
        Long userId = (Long) context.getContext().get("userId");
        return leaveService.getLeaveCountForUser(userId);
    }

    @Tool(name = "create_leave", description = "Create a new leave for a user")
    public EmployeeLeave createLeave(ToolContext context, @ToolParam(description = "Leave date") LocalDate leaveDate) {
        Long userId = (Long) context.getContext().get("userId");
        return leaveService.createLeaveForUser(1L, leaveDate);
    }
}
