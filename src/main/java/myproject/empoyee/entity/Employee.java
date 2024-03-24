package myproject.empoyee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myproject.assignment.entity.Assignment;
import myproject.base.entity.BaseEntity;
import myproject.department.entity.Department;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employees")
@NamedQueries({
        @NamedQuery(
                name = "getEmployeesOrderByTotalHours",
                query = "SELECT e, " +
                        "SUM(a.numberOfHour)" +
                        "FROM Employee e " +
                        "LEFT JOIN e.assignments a " +
                        "WHERE e.department.id = :departmentId " +
                        "GROUP BY e.id " +
                        "ORDER BY SUM(a.numberOfHour) DESC"
        )
})
//@NamedEntityGraph(
//        name = "entityGraphForEmployeesWithProjects",
//        attributeNodes = {
//                @NamedAttributeNode(value = "assignments", subgraph = "assignments-subgraph")
//        },
//        subgraphs = @NamedSubgraph(
//                name = "assignments-subgraph",
//                attributeNodes = {
//                        @NamedAttributeNode("project")
//                }
//        )
//)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private Set<Assignment> assignments;


}