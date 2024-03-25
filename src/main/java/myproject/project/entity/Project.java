package myproject.project.entity;

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
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "projects")
@NamedQuery(
        name = "getProjectsByEmployeeId",
        query = "SELECT p FROM Project p " +
                "LEFT JOIN p.assignments a " +
                "JOIN a.employee e " +
                "WHERE e.id = :employeeId " +
                "ORDER BY p.name"
)
@NamedEntityGraph(
        name = "entityGraphForProjectsByEmployee",
        attributeNodes = {
                @NamedAttributeNode(value = "assignments", subgraph = "assignments-subgraph")
        },
        subgraphs = @NamedSubgraph(
                name = "assignments-subgraph",
                attributeNodes = {
                        @NamedAttributeNode(value = "employee")
                }
        )
)
public class Project extends BaseEntity {
    @Column(nullable = false)
    @NotBlank(message = "Area cannot be blank")
    @Convert(converter = AreaConverter.class)
    private Area area;

    @Column(nullable = false)
    @NotBlank(message = "Project name cannot be blank")
    private String name;

    @ManyToOne
    @JoinColumn(name = "managed_department")
    private Department managedDepartment;

    @OneToMany(mappedBy = "project")
    private Set<Assignment> assignments;
}
