package com.phuoclong.api.infrastructure.configurations.services;

import com.phuoclong.api.infrastructure.Entitis.AccountEntity;
import com.phuoclong.api.infrastructure.Entitis.RoleAccountShare;
import com.phuoclong.api.infrastructure.repositories.RoleAccountShareRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDetailsModelService implements UserDetails {

    private UUID id;
    private String username;
    private String account;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsModelService user = (UserDetailsModelService) o;
        return Objects.equals(id, user.id);
    }

    public static UserDetailsModelService build(AccountEntity accountEntity, Collection<RoleAccountShare> roles) {



        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());

        return new UserDetailsModelService(
                accountEntity.getId(),
                accountEntity.getUsername(),
                accountEntity.getAccount(),
                accountEntity.getEmail(),
                accountEntity.getPassword(),
                authorities);
    }
}
