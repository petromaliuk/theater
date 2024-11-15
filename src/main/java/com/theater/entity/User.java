package com.theater.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Integer id;

    @Column(name = "email", nullable = false, length = 100)
    @NotNull @Email @Size(min = 10, max = 40, message = "Email should be from 2 to 30 characters")
    @JsonView(Views.IdName.class)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    @NotBlank @Size(min = 2, max = 100, message = "Password should be from 2 to 20 characters")
    private String password;

    @Column(name = "role", nullable = false, columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    @JsonView(Views.IdName.class)
    private Role role;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "activation_code", length = 50)
    private String activationCode;

    //public User(String email, String password) { this(1, email, password, Role.VIEWER, true); }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();

        list.add(new SimpleGrantedAuthority("ROLE_" + role));

        return list;
    }

    public String getName(){
        return email.split("@")[0];
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
