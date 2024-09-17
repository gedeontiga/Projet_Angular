package com.studor.orientation_student.entities;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RoleType {
    USER(
        Set.of(PermissionType.USER_GET_ALL_SERVICES)
    ),
    ADMIN(
        Set.of(
            PermissionType.ADMIN_CREATE_ALL,
            PermissionType.ADMIN_READ_ALL,
            PermissionType.ADMIN_UPDATE_ALL,
            PermissionType.ADMIN_DELETE_ALL
        )
    ),
    MANAGER(
        Set.of(
            PermissionType.MANAGER_CREATE_USER,
            PermissionType.MANAGER_READ_ALL_USERS,
            PermissionType.MANAGER_UPDATED_USER,
            PermissionType.MANAGER_DELETE_USER,
            PermissionType.USER_GET_ALL_SERVICES
        )
    );

    @Getter
    private Set<PermissionType> permissions;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = permissions.stream().map(
            permission -> new SimpleGrantedAuthority(permission.name())
        ).collect(Collectors.toList());

        grantedAuthorities.add(new SimpleGrantedAuthority(this.name()));
        return grantedAuthorities;
    }
}
