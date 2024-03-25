package myproject.relatives.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myproject.base.entity.BaseEntity;
import myproject.empoyee.entity.Employee;
import myproject.empoyee.entity.Gender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "relatives")
public class Relative extends BaseEntity {
    @Column(nullable = false)
    @NotBlank(message = "The full name cannot be blank")
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private String phoneNumber;
    @Column
    private String relationship;

    @ManyToOne
    @JoinColumn(name = "emmployee_id")
    private Employee employee;
}
