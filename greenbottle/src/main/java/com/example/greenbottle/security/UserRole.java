package com.example.greenbottle.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.greenbottle.security.UserPermission.*;

public enum UserRole {

    CUSTOMER(Sets.newHashSet(CUSTOMER_BUY,CUSTOMER_SELL)),
    ADMIN(Sets.newHashSet(CUSTOMER_READ,CUSTOMER_DELETE,CUSTOMER_REGISTER,CUSTOMER_UPDATE)),
    ADMINTRAINEE(Sets.newHashSet(CUSTOMER_READ));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {return this.permissions;}

   public Set<GrantedAuthority> getGrantedAuthorities() {
       Set<GrantedAuthority> permissions = getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

      permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
       return permissions;
    }

}
