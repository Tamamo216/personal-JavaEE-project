package myproject.project.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "projects")
public class Project extends BaseEntity {
    @Column(nullable = false)
    @NotBlank(message = "Area cannot be blank")
    @Enumerated(EnumType.STRING)
    private Area area;
    @Column(nullable = false)
    @NotBlank(message = "Project name cannot be blank")
    private String name;
    @ManyToOne
    private Department managedDepartment;
}