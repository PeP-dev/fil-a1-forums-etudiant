package org.filmt.projetagile.roles.model;

import lombok.Getter;

@Getter
public enum Role {
    ETUDIANT(1),
    ADMINISTRATEUR(2);
    private final int code;
    Role(int code) {
        this.code = code;
    }
}
