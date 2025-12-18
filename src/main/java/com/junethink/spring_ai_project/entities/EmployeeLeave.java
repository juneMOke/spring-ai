package com.junethink.spring_ai_project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employee_leave")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "leave_date", nullable = false)
    private LocalDate leaveDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserApp userApp;

    public EmployeeLeave(LocalDate leaveDate, UserApp userApp) {
        this.leaveDate = leaveDate;
        this.userApp = userApp;
    }


}
