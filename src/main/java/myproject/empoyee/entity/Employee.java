package myproject.empoyee.entity;

import myproject.base.entity.BaseEntity;
import myproject.department.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String middleName;
    @Column(nullable = false)
    private String lastName;
    @Transient
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private int salary;

    @ManyToOne
    @JoinColumn(name = "deptid")
    private Department department;


}