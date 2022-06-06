package org.filmt.projetagile.auth.model;

import java.util.Collection;
import java.util.Collections;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import org.thymeleaf.util.StringUtils;

@Getter
@Setter
public class UserModel extends User {
    private String email = Strings.EMPTY;

    private String avatarUrl = Strings.EMPTY;

    private String note = Strings.EMPTY;

    private String pseudo = Strings.EMPTY;

    private boolean isGlobalAdmin = false;

    public UserModel(final String username, final String password) {
        super(username, password, Collections.emptyList());
    }

    public UserModel(final String username, final String password, String email, String note, String pseudo, boolean isGlobalAdmin) {
        super(username, password, Collections.emptyList());
        this.email = email;
        this.note = note;
        this.pseudo = pseudo;
        this.isGlobalAdmin = isGlobalAdmin;
    }

    public UserModel(final String username, final String password, final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserModel(final String username, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

}
