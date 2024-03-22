package myproject.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myproject.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
@NamedQueries(
        @NamedQuery(name = "User_FindByEmail", query = "SELECT u from User u WHERE email = :email")
)
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    @NotBlank(message = "email cannot be blank")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "password cannot be blank")
    private String password;

    private String displayName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}
