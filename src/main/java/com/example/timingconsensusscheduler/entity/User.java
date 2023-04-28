package com.example.timingconsensusscheduler.entity;

import com.example.timingconsensusscheduler.dto.UserBaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NamedNativeQuery(
        name = "User.findAllByProjection",
        query = "select id, name, email, role from users",
        resultSetMapping = "Mapping.UserBaseDto"
)
@SqlResultSetMapping(
    name = "Mapping.UserBaseDto",
    classes = @ConstructorResult(
        targetClass = UserBaseDto.class,
        columns = {
                @ColumnResult(name = "id"),
                @ColumnResult(name = "name"),
                @ColumnResult(name = "email"),
                @ColumnResult(name = "role"),
        }
    )
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    private String password;
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
