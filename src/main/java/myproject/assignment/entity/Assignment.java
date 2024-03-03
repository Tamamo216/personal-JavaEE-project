package myproject.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myproject.base.entity.BaseEntity;
import myproject.empoyee.entity.Employee;
import myproject.project.entity.Project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "assignments")
public class Assignment extends BaseEntity {
    @Column
    private int numberOfHours;
    @ManyToOne
    private Employee employeeId;
    @ManyToOne
    private Project projectId;
}
