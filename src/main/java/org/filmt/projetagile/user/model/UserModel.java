package org.filmt.projetagile.user.model;

import java.util.Collection;
import java.util.Collections;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel implements UserDetails {

    private String email = Strings.EMPTY;

    private String avatarUrl = Strings.EMPTY;

    private String note = Strings.EMPTY;

    private String pseudo = Strings.EMPTY;

    private boolean isGlobalAdmin = false;

    @JsonIgnore
    private User user = null;

    public UserModel() {
    }

    public UserModel(final String username, final String password) {
        this.user = new User(username, password, Collections.emptyList());
    }

    public UserModel(final String username, final String password, String email, String note, String pseudo, boolean isGlobalAdmin) {
        this.user = new User(username, password, Collections.emptyList());
        this.email = email;
        this.note = note;
        this.pseudo = pseudo;
        this.isGlobalAdmin = isGlobalAdmin;
    }

    @Builder
    public UserModel(final boolean isGlobalAdmin, final String username, final String email, final String note, final String pseudo, final String avatarUrl) {
        this.user = new User(username, "", Collections.emptyList());
        this.email = email;
        this.note = note;
        this.pseudo = pseudo;
        this.isGlobalAdmin = isGlobalAdmin;
        this.avatarUrl = avatarUrl;
    }

    public UserModel(final String username, final String password, final Collection<? extends GrantedAuthority> authorities) {
        this.user = new User(username, password, authorities);
    }

    public UserModel(final String username, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities) {
        this.user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getAuthorities();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @JsonProperty("user_name")
    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @JsonIgnore
    @JsonProperty("non_expired")
    @Override
    public boolean isAccountNonExpired() {
        return this.user.isAccountNonExpired();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.user.isAccountNonLocked();
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.isCredentialsNonExpired();
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.user.isEnabled();
    }
}
