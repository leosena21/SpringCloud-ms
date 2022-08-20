package com.projeto.hroauth.entities;

import lombok.*;

import java.io.Serializable;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements Serializable {
    private static final long serialVersionUID = 1327666669408038219L;


    private Long id;
    private String roleName;

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return roleName.equals(role.roleName);
    }

    @Override
    public int hashCode() {
        return roleName.hashCode();
    }
}
