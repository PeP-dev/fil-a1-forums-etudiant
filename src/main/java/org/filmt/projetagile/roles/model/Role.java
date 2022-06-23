package org.filmt.projetagile.roles.model;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum Role {
    ETUDIANT(1),
    ADMINISTRATEUR(2),
    PROPRIETAIRE(3);
    private final int code;
    Role(int code) {
        this.code = code;
    }

    public static Role getByCode(int i) {
        return Arrays.stream(Role.values()).filter(roles -> roles.getCode() == i).findFirst().orElseThrow(()->new RuntimeException("Couldn't find a role with code "+i));
    }

    public boolean isAtLeast(Role role) {
        return this.getCode() >= role.getCode();
    }
}
