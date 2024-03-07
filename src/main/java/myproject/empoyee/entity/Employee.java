package myproject.empoyee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myproject.base.entity.BaseEntity;
import myproject.department.entity.Department;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employees")
@NamedQueries(
        @NamedQuery(
                name = "getEmployeesWithProjects",
                query = "SELECT e, p FROM Employee e " +
                        "LEFT JOIN Assignment a ON e.id = a.employee " +
                        "JOIN Project p ON p.id = a.project " +
                        "ORDER BY e.firstName "
        )
)
public class Employee extends BaseEntity {
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @Column(nullable = false)
    @NotBlank(message = "Middle name cannot be blank")
    private String middleName;
    @Column(nullable = false)
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private Long salary;

    @ManyToOne
    @JoinColumn(name = "deptid")
    private Department department;


}