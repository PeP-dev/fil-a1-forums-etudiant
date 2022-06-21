package org.filmt.projetagile.auth.model;

public enum GroupRole {
    OWNER(10),
    ADMIN(5),
    USER(1);
    private int priority;

    GroupRole(int priority) {
        this.priority = priority;
    }

    public boolean higherOrEqualTo(GroupRole role) {
        return this.priority >= role.priority;
    }
}
