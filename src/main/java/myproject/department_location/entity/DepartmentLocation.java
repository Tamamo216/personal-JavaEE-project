package myproject.department_location.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myproject.base.entity.BaseEntity;
import myproject.department.entity.Department;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "department_locations")
public class DepartmentLocation extends BaseEntity {
    @Column
    private String location;
    @ManyToOne
    @JoinColumn(name = "deptid")
    private Department deptId;
}